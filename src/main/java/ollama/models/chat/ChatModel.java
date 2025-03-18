package ollama.models.chat;

import java.util.List;

public class ChatModel {
    /** The model name to be used for inference. */
    String model;
    /** Messages in the conversation. */
    List<Message> messages;
    /** Indicates whether the response should be streamed. */
    boolean stream;

    public ChatModel(String model, List<Message> messages, boolean stream) {
        this.model = model;
        this.messages = messages;
        this.stream = stream;
    }

    public ChatModel() {
    }

    public ChatModel(boolean stream, List<Message> messages) {
        this.stream = stream;
        this.messages = messages;
    }

    public ChatModel(List<Message> messages) {
        this.stream = false;
        this.messages = messages;
    }

    public ChatModel updateChat(List<Message> messages){
        this.messages.addAll(messages);
        return this;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }
}
