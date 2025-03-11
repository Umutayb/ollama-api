package ollama.models;

import ollama.Ollama;

public class PromptModel {
    String model;
    String prompt;
    boolean stream;
    Object format;

    public PromptModel(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }

    public <ResponseFormat> PromptModel(String model, String prompt, boolean stream, Class<ResponseFormat> format, String... requiredFields) {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
        this.format = Ollama.getSchema(format, requiredFields);
    }

    public PromptModel(String model, String prompt, boolean stream) {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
    }

    public Object getFormat() {
        return format;
    }

    public void setFormat(Object format) {
        this.format = format;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    static class Options{
        double temperature;
    }

    public static class Message {
        String role;
        String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
