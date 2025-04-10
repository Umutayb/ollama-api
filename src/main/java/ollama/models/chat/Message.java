package ollama.models.chat;

/**
 * Represents a message in a chat conversation.  This class encapsulates the role of the sender
 * and the content of the message.
 *
 * @author Umut Ay Bora
 * @version 0.0.3
 */
public class Message {
    Role role;
    String content;

    /**
     * Constructs a new Message object with the specified role and content.
     *
     * @param role The role of the sender (e.g., user, assistant).
     * @param content The text content of the message.
     */
    public Message(Role role, String content) {
        this.content = content;
        this.role = role;
    }

    /**
     * Default constructor for the Message class.  Useful for deserialization or creating
     * an empty message object.
     */
    public Message() {
    }

    /**
     * Sets the role of the sender.
     *
     * @param role The role to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets the content of the message.
     *
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the role of the sender.
     *
     * @return The role of the sender.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }
}