package ollama.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import static utils.mapping.MappingUtilities.Json.Schema.generateSchema;
import static utils.mapping.MappingUtilities.Json.mapper;
import static utils.reflection.ReflectionUtilities.getAllFieldNames;

/**
 * A utility class providing various helper methods for processing JSON schemas and parsing responses.
 */
public class Utilities {

    /**
     * Generates a JSON schema for the given class, with optional required fields.
     *
     * @param clazz The class for which the JSON schema should be generated.
     * @param requiredFields A varargs array of field names to mark as "required".
     *                       If requiredFields is left empty, all fields of the specific class with be added
     *                       as required by default.
     * @param <T> The generic class type.
     * @return A {@code JsonNode} representing the generated schema.
     */
    public static <T> JsonNode getSchema(Class<T> clazz, String... requiredFields) {
        JsonSchema schema = generateSchema(clazz);
        assert schema != null;
        schema.setId(null);
        return requiredFields != null && requiredFields.length > 0 ?
                addRequiredFields(schema, requiredFields) :
                requiredFields != null ?
                        addRequiredFields(schema, getAllFieldNames(clazz)) :
                        mapper.valueToTree(schema);
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
