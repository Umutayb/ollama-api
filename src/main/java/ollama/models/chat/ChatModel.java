package ollama.models.chat;

import java.util.List;

/**
 * Represents a chat model with its associated messages and streaming option.
 * This class is used to configure and send chat requests to an Ollama model.
 */
public class ChatModel {

    /**
     * The model name to be used for inference.
     */
    private String model;

    /**
     * Messages in the conversation.
     */
    private List<Message> messages;

    /**
     * Indicates whether the response should be streamed.
     */
    private boolean stream;

    /**
     * Constructs a ChatModel with the specified model, messages, and streaming option.
     *
     * @param model    The name of the model to use.
     * @param messages The list of messages in the conversation.
     * @param stream   Whether to stream the response.
     */
    public ChatModel(String model, List<Message> messages, boolean stream) {
        this.model = model;
        this.messages = messages;
        this.stream = stream;
    }

    /**
     * Constructs an empty ChatModel.
     */
    public ChatModel() {
    }

    /**
     * Constructs a ChatModel with the specified streaming option and messages.
     *
     * @param stream   Whether to stream the response.
     * @param messages The list of messages in the conversation.
     */
    public ChatModel(boolean stream, List<Message> messages) {
        this.stream = stream;
        this.messages = messages;
    }

    /**
     * Constructs a ChatModel with the specified messages and defaults streaming to false.
     *
     * @param messages The list of messages in the conversation.
     */
    public ChatModel(List<Message> messages) {
        this.stream = false;
        this.messages = messages;
    }

    /**
     * Updates the chat with new messages.
     *
     * @param messages The new messages to add to the conversation.
     * @return The updated ChatModel instance.
     */
    public ChatModel updateChat(List<Message> messages) {
        this.messages.addAll(messages);
        return this;
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
     * Sets the model name.
     *
     * @param model The model name to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the messages.
     *
     * @return The messages.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the messages.
     *
     * @param messages The messages to set.
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Checks if the response should be streamed.
     *
     * @return True if the response should be streamed, false otherwise.
     */
    public boolean isStream() {
        return stream;
    }

    /**
     * Sets the streaming option.
     *
     * @param stream True to stream the response, false otherwise.
     */
    public void setStream(boolean stream) {
        this.stream = stream;
    }
}
