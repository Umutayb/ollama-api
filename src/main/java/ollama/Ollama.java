package ollama;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import retrofit2.Call;
import retrofit2.Response;
import utils.MappingUtilities;
import ollama.models.PromptModel;
import ollama.models.ResponseModel;

import static utils.MappingUtilities.Json.Schema.generateSchema;
import static utils.MappingUtilities.Json.mapper;

/**
 * The Ollama class provides API utilities for interacting with an external service.
 * It facilitates API communication, request handling, and JSON schema generation.
 */
public class Ollama extends ApiUtilities {

    /**
     * Service interface for making API calls.
     */
    OllamaServices ollamaServices;

    /**
     * Constructs an instance of Ollama with a specified base URL.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl) {
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(1200)
                .setRequestLogging(true)
                .generate(OllamaServices.class);
    }

    /**
     * Sends an inference request with a given message.
     *
     * @param message The prompt message model.
     * @return A ResponseModel containing the API response.
     */
    public ResponseModel inference(PromptModel message) {
        log.info("Messaging " + message.getModel() + ".");
        Call<ResponseModel> messageCall = ollamaServices.postMessage(message);
        return perform(messageCall, true, false, Response.class);
    }

    /**
     * Sends an inference request and maps the response to a specified type.
     *
     * @param message       The prompt message model.
     * @param responseType  The class type to map the response to.
     * @param requiredFields Optional required fields for JSON schema generation.
     * @param <ResponseType> The generic response type.
     * @return The API response mapped to the specified type.
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    public <ResponseType> ResponseType inference(
            PromptModel message,
            Class<ResponseType> responseType,
            String... requiredFields
    ) throws JsonProcessingException {
        log.info("Messaging " + message.getModel() + ".");
        message.setFormat(getSchema(responseType, requiredFields));
        Call<ResponseModel> messageCall = ollamaServices.postMessage(message);
        String response = ((ResponseModel) perform(messageCall, true, false, Response.class)).getResponse();
        return MappingUtilities.Json.fromJsonString(response, responseType);
    }

    /**
     * Generates a JSON schema for the given class, with optional required fields.
     *
     * @param clazz The class for which the JSON schema should be generated.
     * @param requiredFields A varargs array of field names to mark as "required".
     * @return A JsonNode representing the generated schema.
     */
    public static JsonNode getSchema(Class<?> clazz, String... requiredFields) {
        JsonSchema schema = generateSchema(clazz);
        assert schema != null;
        schema.setId(null);
        return addRequiredFields(schema, requiredFields);
    }

    /**
     * Adds required fields to the "required" property of a JSON schema.
     *
     * @param schema The JSON schema to modify.
     * @param requiredFields An array of field names to mark as required.
     * @return The modified JSON schema as a JsonNode.
     */
    private static JsonNode addRequiredFields(JsonSchema schema, String[] requiredFields) {
        JsonNode schemaNode = mapper.valueToTree(schema);

        if (!(schemaNode instanceof ObjectNode root)) {
            throw new IllegalArgumentException("Schema must be an ObjectNode");
        }

        ArrayNode required = root.withArray("required");

        for (String fieldName : requiredFields) {
            required.add(fieldName);
        }

        return mapper.valueToTree(root);
    }
}
