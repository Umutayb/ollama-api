package ollama.models.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatResponse {
    String id;
    long created;
    String model;
    List<Choice> choices;
    String object;
    Usage usage;

    public String getId() {
        return id;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public String getObject() {
        return object;
    }

    public Usage getUsage() {
        return usage;
    }

    public static class Choice {
        int index;
        Object logprobs;
        String finish_reason;
        Message message;

        public int getIndex() {
            return index;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public Message getMessage() {
            return message;
        }
    }

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

        public double getResponse_token_s() {
            return response_token_s;
        }

        public double getPrompt_token_s() {
            return prompt_token_s;
        }

        public long getTotal_duration() {
            return total_duration;
        }

        public long getLoad_duration() {
            return load_duration;
        }

        public int getPrompt_eval_count() {
            return prompt_eval_count;
        }

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public long getPrompt_eval_duration() {
            return prompt_eval_duration;
        }

        public int getEval_count() {
            return eval_count;
        }

        public int getCompletion_tokens() {
            return completion_tokens;
        }

        public long getEval_duration() {
            return eval_duration;
        }

        public String getApproximate_total() {
            return approximate_total;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }

        public CompletionTokensDetails getCompletion_tokens_details() {
            return completion_tokens_details;
        }
    }
     public static class CompletionTokensDetails {
        int reasoning_tokens;
        int accepted_prediction_tokens;
        int rejected_prediction_tokens;

         public int getReasoning_tokens() {
             return reasoning_tokens;
         }

         public int getAccepted_prediction_tokens() {
             return accepted_prediction_tokens;
         }

         public int getRejected_prediction_tokens() {
             return rejected_prediction_tokens;
         }
     }
}
