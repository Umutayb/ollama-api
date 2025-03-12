package ollama.models;

import ollama.Ollama;

/**
 * Data model for sending prompt requests.
 */
public class Prompt {
    /** The model name to be used for inference. */
    String model;

    /** The prompt message to be sent. */
    String prompt;

    /** Indicates whether the response should be streamed. */
    boolean stream;

    /** The format of the response, can be a JSON schema. */
    Object format;

    /**
     * Constructs a PromptModel with a model name and prompt.
     *
     * @param model  The name of the model.
     * @param prompt The input prompt message.
     */
    public Prompt(String model, String prompt) {
        this(model, prompt, false);
    }

    /**
     * Constructs a PromptModel with additional streaming and response format options.
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
        this.format = Ollama.getSchema(format, requiredFields);
    }

    /**
     * Constructs a PromptModel with additional streaming and response format options.
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
     * Constructs a PromptModel with additional streaming and response format options.
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
     * Constructs a PromptModel with a model name, prompt, and streaming option.
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

    public Prompt() {}

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public void setFormat(Object format) {
        this.format = format;
    }

    public String getModel() {
        return model;
    }

    public String getPrompt() {
        return prompt;
    }

    public boolean isStream() {
        return stream;
    }

    public Object getFormat() {
        return format;
    }
}
