package ollama.models.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the response from a chat completion request.
 *
 * @author Umut Ay Bora
 * @version 0.0.3
 */
public class ChatResponse {

    String id;
    long created;
    String model;
    List<Choice> choices;
    String object;
    Usage usage;

    /**
     * Gets the unique identifier for the response.
     *
     * @return The response ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the timestamp of when the response was created (in Unix time).
     *
     * @return The creation timestamp.
     */
    public long getCreated() {
        return created;
    }

    /**
     * Gets the model used to generate the response.
     *
     * @return The model name.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the list of choices generated by the model.
     *
     * @return A list of {@link Choice} objects.
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Gets the type of object returned (typically "chat.completion").
     *
     * @return The object type.
     */
    public String getObject() {
        return object;
    }

    /**
     * Gets the usage statistics for the request.
     *
     * @return A {@link Usage} object containing usage details.
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * Represents a single choice generated by the model.
     */
    public static class Choice {
        int index;
        Object logprobs;
        String finish_reason;
        Message message;

        /**
         * Gets the index of the choice in the list of choices.
         *
         * @return The choice index.
         */
        public int getIndex() {
            return index;
        }

        /**
         * Gets the log probabilities for the tokens in the choice.
         *
         * @return The log probabilities (can be null).
         */
        public Object getLogprobs() {
            return logprobs;
        }

        /**
         * Gets the reason why the generation finished (e.g., "stop", "length").
         *
         * @return The finish reason.
         */
        public String getFinish_reason() {
            return finish_reason;
        }

        /**
         * Gets the message generated by the model.
         *
         * @return The {@link Message} object.
         */
        public Message getMessage() {
            return message;
        }
    }

    /**
     * Represents usage statistics for the request.
     */
    public static class Usage {
        @JsonProperty("response_token/s")
        double response_token_s;
        @JsonProperty("prompt_token/s")
        double prompt_token_s;
        long total_duration;
        long load_duration;
        int prompt_eval_count;
        int prompt_tokens;
        long prompt_eval_duration;
        int eval_count;
        int completion_tokens;
        long eval_duration;
        String approximate_total;
        int total_tokens;
        CompletionTokensDetails completion_tokens_details;

        /**
         * Gets the number of response tokens.
         *
         * @return The response tokens.
         */
        public double getResponse_token_s() {
            return response_token_s;
        }

        /**
         * Gets the number of prompt tokens.
         *
         * @return The prompt tokens.
         */
        public double getPrompt_token_s() {
            return prompt_token_s;
        }

        /**
         * Gets the total duration of the request (in milliseconds).
         *
         * @return The total duration.
         */
        public long getTotal_duration() {
            return total_duration;
        }

        /**
         * Gets the duration of the loading phase (in milliseconds).
         *
         * @return The load duration.
         */
        public long getLoad_duration() {
            return load_duration;
        }

        /**
         * Gets the number of times the prompt was evaluated.
         *
         * @return The prompt evaluation count.
         */
        public int getPrompt_eval_count() {
            return prompt_eval_count;
        }

        /**
         * Gets the number of tokens in the prompt.
         *
         * @return The prompt tokens.
         */
        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        /**
         * Gets the duration of the prompt evaluation phase (in milliseconds).
         *
         * @return The prompt evaluation duration.
         */
        public long getPrompt_eval_duration() {
            return prompt_eval_duration;
        }

        /**
         * Gets the total number of evaluations performed.
         *
         * @return The evaluation count.
         */
        public int getEval_count() {
            return eval_count;
        }

        /**
         * Gets the number of tokens in the completion.
         *
         * @return The completion tokens.
         */
        public int getCompletion_tokens() {
            return completion_tokens;
        }

        /**
         * Gets the duration of the evaluation phase (in milliseconds).
         *
         * @return The evaluation duration.
         */
        public long getEval_duration() {
            return eval_duration;
        }

        /**
         * Gets an approximate total cost.
         *
         * @return The approximate total cost.
         */
        public String getApproximate_total() {
            return approximate_total;
        }

        /**
         * Gets the total number of tokens used (prompt + completion).
         *
         * @return The total tokens.
         */
        public int getTotal_tokens() {
            return total_tokens;
        }

        /**
         * Gets detailed token counts for the completion.
         *
         * @return The {@link CompletionTokensDetails} object.
         */
        public CompletionTokensDetails getCompletion_tokens_details() {
            return completion_tokens_details;
        }
    }

    /**
     * Represents detailed token counts for the completion.
     */
    public static class CompletionTokensDetails {
        // Add fields here if available in the actual class
    }
}