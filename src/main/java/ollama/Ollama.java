package ollama;

import api_assured.ApiUtilities;
import api_assured.Caller;
import api_assured.ServiceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import context.ContextStore;
import retrofit2.Call;
import retrofit2.Response;
import utils.mapping.MappingUtilities;
import ollama.models.Prompt;
import ollama.models.OllamaResponse;

import static utils.mapping.MappingUtilities.Json.Schema.generateSchema;
import static utils.mapping.MappingUtilities.Json.mapper;
import static utils.reflection.ReflectionUtilities.getAllFieldNames;

/**
 * The {@code Ollama} class provides API utilities for interacting with an external service.
 * It facilitates API communication, request handling, and JSON schema generation.
 */
public class Ollama extends ApiUtilities {

    /**
     * Service interface for making API calls.
     */
    OllamaServices ollamaServices;

    /**
     * Default model to communicate.
     */
    String defaultModel;

    /**
     * Flag to enable or disable logging of API responses.
     */
    boolean logsResponses;

    /**
     * Flag to enable or disable logging of API requests.
     */
    boolean logsRequests;

    /**
     * The read timeout duration for API calls in milliseconds.
     */
    int readTimeout;

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        keepLogs(logsRequests);
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .printHeaders(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl, String defaultModel) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        this.defaultModel = defaultModel;
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Sends an inference request with a given message.
     *
     * @param prompt The {@code PromptModel} containing the prompt message.
     * @return A {@code ResponseModel} containing the API response.
     */
    public OllamaResponse inference(Prompt prompt) {
        prompt.setModel(prompt.getModel() == null ? defaultModel : prompt.getModel());
        log.info("Messaging " + prompt.getModel() + ".");
        Call<OllamaResponse> inferenceCall = ollamaServices.postMessage(prompt);
        return perform(inferenceCall, true, logsResponses, Response.class);
    }

    /**
     * Sends an inference request and maps the response to a specified type.
     *
     * @param prompt       The {@code PromptModel} containing the prompt message.
     * @param responseType  The class type to map the response to.
     * @param requiredFields Optional required fields for JSON schema generation.
     * @param <T> The generic response type.
     * @return The API response mapped to the specified type.
     * @throws RuntimeException If JSON processing fails.
     */
    public <T> T inference(
            Prompt prompt,
            Class<T> responseType,
            String... requiredFields
    ) {
        try {
            prompt.setModel(prompt.getModel() == null ? defaultModel : prompt.getModel());
            log.info("Messaging " + prompt.getModel() + ".");
            prompt.setFormat(getSchema(responseType, requiredFields));
            Call<OllamaResponse> inferenceCall = ollamaServices.postMessage(prompt);
            String response = ((OllamaResponse) perform(
                    inferenceCall,
                    true,
                    logsResponses,
                    Response.class)
            ).getResponse();
            return MappingUtilities.Json.fromJsonString(response, responseType);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a JSON schema for the given class, with optional required fields.
     *
     * @param clazz The class for which the JSON schema should be generated.
     * @param requiredFields A varargs array of field names to mark as "required".
     * @param <T> The generic class type.
     * @return A {@code JsonNode} representing the generated schema.
     */
    public static <T> JsonNode getSchema(Class<T> clazz, String... requiredFields) {
        JsonSchema schema = generateSchema(clazz);
        assert schema != null;
        schema.setId(null);
        return requiredFields.length > 0 ?
                addRequiredFields(schema, requiredFields) :
                addRequiredFields(schema, getAllFieldNames(clazz));
    }

    /**
     * Adds required fields to the "required" property of a JSON schema.
     *
     * @param schema The JSON schema to modify.
     * @param requiredFields An array of field names to mark as required.
     * @return The modified JSON schema as a {@code JsonNode}.
     * @throws IllegalArgumentException If the schema is not an {@code ObjectNode}.
     */
    private static JsonNode addRequiredFields(JsonSchema schema, String[] requiredFields) {
        JsonNode schemaNode = mapper.valueToTree(schema);

        if (!(schemaNode instanceof ObjectNode root))
            throw new IllegalArgumentException("Schema must be an ObjectNode");

        ArrayNode required = root.withArray("required");

        for (String fieldName : requiredFields) required.add(fieldName);

        return mapper.valueToTree(root);
    }
}