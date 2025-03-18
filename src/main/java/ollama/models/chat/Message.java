package ollama.models.chat;

public class Message {
    Role role;
    String content;

    public Message(Role role, String content) {
        this.content = content;
        this.role = role;
    }

    public Message() {
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Role getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
