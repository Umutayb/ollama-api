package ollama.utilities;

/**
 * A record representing a response with a thought and an associated response message.
 *
 * @param thought The extracted thought from the response.
 * @param response The corresponding response message.
 */
public record COTResponse(String thought, String response) {
    /**
     * Factory method to create a new {@code COTResponse} instance.
     *
     * @param thought  The extracted thought.
     * @param response The corresponding response message.
     * @return A new {@code COTResponse} instance.
     */
    public static COTResponse of(String thought, String response) {
        return new COTResponse(thought, response);
    }

    /**
     * Parses a response string to extract a "thinking" block and its corresponding response.
     *
     * @param responseString The input response string containing a thinking block.
     * @return A {@code COTResponse} containing the extracted thought and response message.
     */
    public static COTResponse thinkingBlockParser(String responseString) {
        // Extract the thinking block
        int thoughtStart = responseString.indexOf("<think>") + "<think>".length();
        int thoughtEnd = responseString.indexOf("</think>");
        int responseStart = thoughtEnd + "</think>".length();

        // Extract thinking block and response block
        String thinkingBlock = responseString.substring(thoughtStart, thoughtEnd).trim();
        String responseBlock = responseString.substring(responseStart).trim();

        return COTResponse.of(thinkingBlock, responseBlock);
    }
}