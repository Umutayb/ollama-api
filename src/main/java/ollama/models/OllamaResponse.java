package ollama.models;

import java.util.List;

public class OllamaResponse {
    String model;
    String created_at;
    String response;
    boolean done;
    String done_reason;
    List<Integer> context;
    long total_duration;
    long load_duration;
    int prompt_eval_count;
    long prompt_eval_duration;
    int eval_count;
    long eval_duration;

    public OllamaResponse() {}

    public OllamaResponse(String model, String created_at, String response, boolean done, String done_reason, List<Integer> context, long total_duration, long load_duration, int prompt_eval_count, long prompt_eval_duration, int eval_count, long eval_duration) {
        this.model = model;
        this.created_at = created_at;
        this.response = response;
        this.done = done;
        this.done_reason = done_reason;
        this.context = context;
        this.total_duration = total_duration;
        this.load_duration = load_duration;
        this.prompt_eval_count = prompt_eval_count;
        this.prompt_eval_duration = prompt_eval_duration;
        this.eval_count = eval_count;
        this.eval_duration = eval_duration;
    }

    public String getModel() {
        return model;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getResponse() {
        return response;
    }

    public boolean isDone() {
        return done;
    }

    public String getDone_reason() {
        return done_reason;
    }

    public List<Integer> getContext() {
        return context;
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

    public long getPrompt_eval_duration() {
        return prompt_eval_duration;
    }

    public int getEval_count() {
        return eval_count;
    }

    public long getEval_duration() {
        return eval_duration;
    }
}
