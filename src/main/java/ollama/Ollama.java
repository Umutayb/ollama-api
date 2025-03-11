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

public class Ollama extends ApiUtilities {

    OllamaServices ollamaServices;

    public Ollama(String baseUrl){
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(1200)
                .setRequestLogging(true)
                .generate(OllamaServices.class);
    }

    public ResponseModel inference(PromptModel message){
        log.info("Messaging " + message.getModel() + ".");
        Call<ResponseModel> messageCall = ollamaServices.postMessage(message);
        return perform(messageCall, true, false, Response.class);
    }

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
     * Generates a JSON schema for the given class, with the option to specify required fields.
     * This method uses the Jackson library to generate the schema and customize it based on the provided required fields.
     * It sets the ID of the schema and its nested schemas to null and adds the required fields to the schema's "required" property.
     *
     * @param clazz The class for which the JSON schema should be generated.
     * @param requiredFields A varargs array of field names that should be marked as "required" in the schema.
     * @return A JsonNode representing the generated schema, or null if an exception occurs during generation.
     */
    public static JsonNode getSchema(Class<?> clazz, String... requiredFields) {
        JsonSchema schema = generateSchema(clazz);
        assert schema != null;
        schema.setId(null);
        return addRequiredFields(schema, requiredFields);
    }

    /**
     * Adds the specified required fields to the "required" property of a JSON schema.
     * This method adds each field name from the provided array to the "required" property of the schema.
     *
     * @param schema         The JSON schema to modify.
     * @param requiredFields An array of field names that should be marked as required in the schema.
     * @return
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

        // Convert the modified JsonNode back to JsonSchema
        return mapper.valueToTree(root);
    }
}
