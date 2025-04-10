package ollama.models.chat;

/**
 * Defines the possible roles for a message in a chat conversation.
 * This enum represents who sent the message: the user, the assistant (e.g., the AI model),
 * or the system (e.g., initial instructions).
 *
 * @author Umut Ay Bora
 * @version 0.0.3
 */
public enum Role {
    /**
     * Represents a message sent by the AI assistant.
     */
    assistant,

    /**
     * Represents a message sent by the system, often containing initial instructions
     * or context for the conversation.
     */
    system,

    /**
     * Represents a message sent by the user.
     */
    user
}