package ollama.models.inference;

import java.util.List;

/**
 * Represents the response received from an Ollama model inference request.
 */
public class InferenceResponse {
    /** The model name used for the inference. */
    private String model;

    /** The timestamp when the response was created. */
    private String created_at;

    /** The actual response content. */
    private String response;

    /** Indicates whether the inference process is completed. */
    private boolean done;

    /** The reason why the inference process completed. */
    private String done_reason;

    /** The context of the response, represented as a list of integers. */
    private List<Integer> context;

    /** The total duration of the inference process in milliseconds. */
    private long total_duration;

    /** The duration taken to load the model in milliseconds. */
    private long load_duration;

    /** The number of tokens evaluated in the prompt. */
    private int prompt_eval_count;

    /** The duration taken to evaluate the prompt in milliseconds. */
    private long prompt_eval_duration;

    /** The number of tokens evaluated in the response generation. */
    private int eval_count;

    /** The duration taken to evaluate the response in milliseconds. */
    private long eval_duration;

    /**
     * Default constructor for creating an empty OllamaResponse instance.
     */
    public InferenceResponse() {}

    /**
     * Constructs an OllamaResponse with all response parameters.
     *
     * @param model              The model name used for inference.
     * @param created_at         The timestamp when the response was created.
     * @param response           The actual response content.
     * @param done               Whether the inference process is completed.
     * @param done_reason        The reason why the inference process completed.
     * @param context            The response context as a list of integers.
     * @param total_duration     The total duration of the inference process in milliseconds.
     * @param load_duration      The duration taken to load the model in milliseconds.
     * @param prompt_eval_count  The number of tokens evaluated in the prompt.
     * @param prompt_eval_duration The duration taken to evaluate the prompt in milliseconds.
     * @param eval_count         The number of tokens evaluated in the response generation.
     * @param eval_duration      The duration taken to evaluate the response in milliseconds.
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
     * Gets the model name used for inference.
     *
     * @return The model name.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the timestamp when the response was created.
     *
     * @return The creation timestamp.
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Gets the response content.
     *
     * @return The response string.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Checks if the inference process is completed.
     *
     * @return True if the inference is done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Gets the reason why the inference process completed.
     *
     * @return The completion reason.
     */
    public String getDone_reason() {
        return done_reason;
    }

    /**
     * Gets the context of the response.
     *
     * @return The response context as a list of integers.
     */
    public List<Integer> getContext() {
        return context;
    }

    /**
     * Gets the total duration of the inference process.
     *
     * @return The total duration in milliseconds.
     */
    public long getTotal_duration() {
        return total_duration;
    }

    /**
     * Gets the duration taken to load the model.
     *
     * @return The load duration in milliseconds.
     */
    public long getLoad_duration() {
        return load_duration;
    }

    /**
     * Gets the number of tokens evaluated in the prompt.
     *
     * @return The number of evaluated prompt tokens.
     */
    public int getPrompt_eval_count() {
        return prompt_eval_count;
    }

    /**
     * Gets the duration taken to evaluate the prompt.
     *
     * @return The prompt evaluation duration in milliseconds.
     */
    public long getPrompt_eval_duration() {
        return prompt_eval_duration;
    }

    /**
     * Gets the number of tokens evaluated in the response generation.
     *
     * @return The number of evaluated response tokens.
     */
    public int getEval_count() {
        return eval_count;
    }

    /**
     * Gets the duration taken to evaluate the response.
     *
     * @return The response evaluation duration in milliseconds.
     */
    public long getEval_duration() {
        return eval_duration;
    }
}
