package ollama.models;

import ollama.utilities.Utilities;

/**
 * Represents a data model for sending prompt requests to an inference engine.
 */
public class Prompt {
    /** The model name to be used for inference. */
    private String model;

    /** The prompt message to be sent. */
    private String prompt;

    /** Indicates whether the response should be streamed. */
    private boolean stream;

    /** The format of the response, which can be a JSON schema. */
    private Object format;

    /**
     * Constructs a Prompt with a model name and prompt.
     *
     * @param model  The name of the model.
     * @param prompt The input prompt message.
     */
    public Prompt(String model, String prompt) {
        this(model, prompt, false);
    }

    /**
     * Constructs a Prompt with additional streaming and response format options.
     *
     * @param <ResponseFormat> The expected response format type.
     * @param model           The name of the model.
     * @param prompt          The input prompt message.
     * @param stream          Whether the response should be streamed.
     * @param format          The response format as a class type.
     * @param requiredFields  Fields required in the response schema.
     */
    public <ResponseFormat> Prompt(String model, String prompt, boolean stream, Class<ResponseFormat> format, String... requiredFields) {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
        this.format = Utilities.getSchema(format, requiredFields);
    }

    /**
     * Constructs a Prompt with additional response format options.
     *
     * @param <ResponseFormat> The expected response format type.
     * @param model           The name of the model.
     * @param prompt          The input prompt message.
     * @param format          The response format as a class type.
     * @param requiredFields  Fields required in the response schema.
     */
    public <ResponseFormat> Prompt(String model, String prompt, Class<ResponseFormat> format, String... requiredFields) {
        this(model, prompt, false, format, requiredFields);
    }

    /**
     * Constructs a Prompt with additional response format options.
     *
     * @param <ResponseFormat> The expected response format type.
     * @param prompt          The input prompt message.
     * @param format          The response format as a class type.
     * @param requiredFields  Fields required in the response schema.
     */
    public <ResponseFormat> Prompt(String prompt, Class<ResponseFormat> format, String... requiredFields) {
        this(null, prompt, false, format, requiredFields);
    }

    /**
     * Constructs a Prompt with additional response format options.
     *
     * @param <ResponseFormat> The expected response format type.
     * @param model           The name of the model.
     * @param prompt          The input prompt message.
     * @param format          The response format as a class type.
     */
    public <ResponseFormat> Prompt(String model, String prompt, Class<ResponseFormat> format) {
        this(model, prompt, false, format);
    }

    /**
     * Constructs a Prompt with additional response format options.
     *
     * @param <ResponseFormat> The expected response format type.
     * @param prompt          The input prompt message.
     * @param format          The response format as a class type.
     */
    public <ResponseFormat> Prompt(String prompt, Class<ResponseFormat> format) {
        this(null, prompt, false, format);
    }

    /**
     * Constructs a Prompt with a model name, prompt, and streaming option.
     *
     * @param model  The name of the model.
     * @param prompt The input prompt message.
     * @param stream Whether the response should be streamed.
     */
    public Prompt(String model, String prompt, boolean stream) {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
    }

    /**
     * Constructs a Prompt with a prompt message and streaming option.
     *
     * @param prompt The input prompt message.
     * @param stream Whether the response should be streamed.
     */
    public Prompt(String prompt, boolean stream) {
        this.prompt = prompt;
        this.stream = stream;
    }

    /**
     * Constructs a Prompt with only a prompt message.
     *
     * @param prompt The input prompt message.
     */
    public Prompt(String prompt) {
        this(prompt, false);
    }

    /**
     * Default constructor.
     */
    public Prompt() {}

    /**
     * Sets the model name.
     *
     * @param model The model name.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the prompt message.
     *
     * @param prompt The input prompt message.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Sets whether the response should be streamed.
     *
     * @param stream True if the response should be streamed, false otherwise.
     */
    public void setStream(boolean stream) {
        this.stream = stream;
    }

    /**
     * Sets the response format.
     *
     * @param format The response format object.
     */
    public void setFormat(Object format) {
        this.format = format;
    }

    /**
     * Gets the model name.
     *
     * @return The model name.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the prompt message.
     *
     * @return The input prompt message.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Checks whether the response should be streamed.
     *
     * @return True if the response should be streamed, false otherwise.
     */
    public boolean isStream() {
        return stream;
    }

    /**
     * Gets the response format.
     *
     * @return The response format object.
     */
    public Object getFormat() {
        return format;
    }
}
