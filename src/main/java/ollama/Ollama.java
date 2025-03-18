package ollama;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import context.ContextStore;
import okhttp3.Headers;
import ollama.models.chat.ChatModel;
import ollama.models.chat.ChatResponse;
import retrofit2.Call;
import retrofit2.Response;
import utils.mapping.MappingUtilities;
import ollama.models.inference.InferenceModel;
import ollama.models.inference.InferenceResponse;

import static ollama.utilities.Utilities.getSchema;

/**
 * The {@code Ollama} class provides API utilities for interacting with an external service.
 * It facilitates API communication, request handling, and JSON schema generation.
 */
public class Ollama extends ApiUtilities {

    /**
     * Service interface for making API calls.
     */
    OllamaServices ollamaServices;

    /**
     * Default model to communicate.
     */
    String defaultModel;

    /**
     * Flag to enable or disable logging of API responses.
     */
    boolean logsResponses;

    /**
     * Flag to enable or disable logging of API requests.
     */
    boolean logsRequests;

    /**
     * The read timeout duration for API calls in milliseconds.
     */
    int readTimeout;

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        keepLogs(logsRequests);
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .printHeaders(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL and authorisation header.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl, Headers authorisationHeader) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        keepLogs(logsRequests);
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setHeaders(authorisationHeader)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .printHeaders(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL, a default model and authorisation header.
     *
     * @param baseUrl The base URL of the API service.
     */
    public Ollama(String baseUrl, String defaultModel, Headers authorisationHeader) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        this.defaultModel = defaultModel;
        keepLogs(logsRequests);
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setHeaders(authorisationHeader)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .printHeaders(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL and a default model.
     *
     * @param baseUrl The base URL of the API service.
     * @param defaultModel To set the default model to be messaged.
     */
    public Ollama(String baseUrl, String defaultModel) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        this.defaultModel = defaultModel;
        ollamaServices = new ServiceGenerator()
                .setBASE_URL(baseUrl)
                .setReadTimeout(readTimeout)
                .setRequestLogging(logsRequests)
                .generate(OllamaServices.class);
    }

    /**
     * Sends an inference request with a given message.
     *
     * @param prompt The {@code PromptModel} containing the prompt message.
     * @return A {@code ResponseModel} containing the API response.
     */
    public InferenceResponse inference(InferenceModel prompt) {
        prompt.setModel(prompt.getModel() == null ? defaultModel : prompt.getModel());
        log.info("Inference with " + prompt.getModel() + ".");
        Call<InferenceResponse> inferenceCall = ollamaServices.generate(prompt);
        return perform(inferenceCall, true, logsResponses, Response.class);
    }

    /**
     * Acquires available models.
     *
     */
    public Object getModels() {
        log.info("Getting models.");
        Call<Object> inferenceCall = ollamaServices.getModels();
        return perform(inferenceCall, true, logsResponses, Response.class);
    }

    /**
     * Sends a chat message with a given message.
     *
     * @param prompt The {@code PromptModel} containing the prompt message.
     * @return A {@code ResponseModel} containing the API response.
     */
    public ChatResponse chat(ChatModel prompt) {
        prompt.setModel(prompt.getModel() == null ? defaultModel : prompt.getModel());
        log.info("Messaging " + prompt.getModel() + ".");
        Call<ChatResponse> inferenceCall = ollamaServices.chat(prompt);
        return perform(inferenceCall, true, logsResponses, Response.class);
    }

    /**
     * Sends an inference request and maps the response to a specified type.
     *
     * @param prompt       The {@code PromptModel} containing the prompt message.
     * @param responseType  The class type to map the response to.
     * @param requiredFields Optional required fields for JSON schema generation.
     * @param <T> The generic response type.
     * @return The API response mapped to the specified type.
     * @throws RuntimeException If JSON processing fails.
     */
    public <T> T inference(InferenceModel prompt, Class<T> responseType, String... requiredFields) {
        try {
            prompt.setModel(prompt.getModel() == null ? defaultModel : prompt.getModel());
            log.info("Inference with " + prompt.getModel() + ".");
            prompt.setFormat(getSchema(responseType, requiredFields));
            Call<InferenceResponse> inferenceCall = ollamaServices.generate(prompt);
            String response = ((InferenceResponse) perform(
                    inferenceCall,
                    true,
                    logsResponses,
                    Response.class)
            ).getResponse();
            return MappingUtilities.Json.fromJsonString(response, responseType);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public boolean isLogsRequests() {
        return logsRequests;
    }

    public void setLogsRequests(boolean logsRequests) {
        this.logsRequests = logsRequests;
    }

    public boolean isLogsResponses() {
        return logsResponses;
    }

    public void setLogsResponses(boolean logsResponses) {
        this.logsResponses = logsResponses;
    }

    public String getDefaultModel() {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel;
    }

    public OllamaServices getOllamaServices() {
        return ollamaServices;
    }

    public void setOllamaServices(OllamaServices ollamaServices) {
        this.ollamaServices = ollamaServices;
    }
}