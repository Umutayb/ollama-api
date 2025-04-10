package ollama.models.inference;

import java.util.List;

/**
 * Represents the response received from an Ollama model inference request.
 * This class contains details about the model used, response metadata,
 * execution durations, and token evaluation counts.
 *
 * @author Umut Ay Bora
 * @version 0.0.1
 */
public class InferenceResponse {
    /** The name of the model used for inference. */
    private String model;

    /** The timestamp when the response was generated. */
    private String created_at;

    /** The actual response content. */
    private String response;

    /** Indicates whether the inference process has completed. */
    private boolean done;

    /** The reason for the completion of the inference process. */
    private String done_reason;

    /** The context of the response, represented as a list of token IDs. */
    private List<Integer> context;

    /** The total duration of the inference process in milliseconds. */
    private long total_duration;

    /** The time taken to load the model in milliseconds. */
    private long load_duration;

    /** The number of tokens processed in the input prompt. */
    private int prompt_eval_count;

    /** The time taken to evaluate the input prompt in milliseconds. */
    private long prompt_eval_duration;

    /** The number of tokens generated in the response. */
    private int eval_count;

    /** The time taken to generate the response in milliseconds. */
    private long eval_duration;

    /**
     * Default constructor for creating an empty InferenceResponse instance.
     */
    public InferenceResponse() {}

    /**
     * Constructs an InferenceResponse with all response parameters.
     *
     * @param model               The model name used for inference.
     * @param created_at          The timestamp when the response was generated.
     * @param response            The actual response content.
     * @param done                Whether the inference process has completed.
     * @param done_reason         The reason for the completion of the inference process.
     * @param context             The response context as a list of token IDs.
     * @param total_duration      The total duration of the inference process in milliseconds.
     * @param load_duration       The time taken to load the model in milliseconds.
     * @param prompt_eval_count   The number of tokens processed in the input prompt.
     * @param prompt_eval_duration The time taken to evaluate the input prompt in milliseconds.
     * @param eval_count          The number of tokens generated in the response.
     * @param eval_duration       The time taken to generate the response in milliseconds.
     */
    public InferenceResponse(String model, String created_at, String response, boolean done, String done_reason,
                             List<Integer> context, long total_duration, long load_duration,
                             int prompt_eval_count, long prompt_eval_duration, int eval_count, long eval_duration) {
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

    /**
     * Returns the name of the model used for inference.
     *
     * @return The model name.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the timestamp when the response was generated.
     *
     * @return The creation timestamp.
     */
    public String getCreatedAt() {
        return created_at;
    }

    /**
     * Returns the response content.
     *
     * @return The response string.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Checks if the inference process has completed.
     *
     * @return {@code true} if the inference is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the reason for the completion of the inference process.
     *
     * @return The completion reason.
     */
    public String getDoneReason() {
        return done_reason;
    }

    /**
     * Returns the response context as a list of token IDs.
     *
     * @return The response context.
     */
    public List<Integer> getContext() {
        return context;
    }

    /**
     * Returns the total duration of the inference process in milliseconds.
     *
     * @return The total duration.
     */
    public long getTotalDuration() {
        return total_duration;
    }

    /**
     * Returns the time taken to load the model in milliseconds.
     *
     * @return The load duration.
     */
    public long getLoadDuration() {
        return load_duration;
    }

    /**
     * Returns the number of tokens processed in the input prompt.
     *
     * @return The number of prompt tokens evaluated.
     */
    public int getPromptEvalCount() {
        return prompt_eval_count;
    }

    /**
     * Returns the time taken to evaluate the input prompt in milliseconds.
     *
     * @return The prompt evaluation duration.
     */
    public long getPromptEvalDuration() {
        return prompt_eval_duration;
    }

    /**
     * Returns the number of tokens generated in the response.
     *
     * @return The number of response tokens evaluated.
     */
    public int getEvalCount() {
        return eval_count;
    }

    /**
     * Returns the time taken to generate the response in milliseconds.
     *
     * @return The response evaluation duration.
     */
    public long getEvalDuration() {
        return eval_duration;
    }
}
