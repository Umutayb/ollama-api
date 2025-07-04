package ollama;

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
import wasapi.WasapiUtilities;
import wasapi.WasapiClient;

/**
 * The {@code Ollama} class provides API utilities for interacting with an external service.
 * It facilitates API communication, request handling, and JSON schema generation.
 */
public class Ollama extends WasapiUtilities {

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
        ollamaServices = new WasapiClient.Builder()
                .baseUrl(baseUrl)
                .readTimeout(readTimeout)
                .logRequestBody(logsRequests)
                .printHeaders(logsRequests)
                .build(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL and authorization header.
     *
     * @param baseUrl         The base URL of the API service.
     * @param authorisationHeader The authorization header to include in requests.
     */
    public Ollama(String baseUrl, Headers authorisationHeader) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        keepLogs(logsRequests);
        ollamaServices = new WasapiClient.Builder()
                .baseUrl(baseUrl)
                .headers(authorisationHeader)
                .readTimeout(readTimeout)
                .logRequestBody(logsRequests)
                .printHeaders(logsRequests)
                .build(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL and authorization header.
     *
     * @param baseUrl         The base URL of the API service.
     * @param defaultModel     The default model to use for requests.
     * @param authorisationKey The authorization key to authorise the requests.
     */
    public Ollama(String baseUrl, String defaultModel, String authorisationKey) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        this.defaultModel = defaultModel;
        keepLogs(logsRequests);
        ollamaServices = new WasapiClient.Builder()
                .baseUrl(baseUrl)
                .headers(Headers.of("Authorization", "Bearer " + authorisationKey))
                .readTimeout(readTimeout)
                .logRequestBody(logsRequests)
                .printHeaders(logsRequests)
                .build(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL and authorization header.
     *
     * @param baseUrl         The base URL of the API service.
     * @param authorisationKey The authorization key to authorise the requests.
     */
    public Ollama(String baseUrl, String authorisationKey) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        keepLogs(logsRequests);
        ollamaServices = new WasapiClient.Builder()
                .baseUrl(baseUrl)
                .headers(Headers.of("Authorization", "Bearer " + authorisationKey))
                .readTimeout(readTimeout)
                .logRequestBody(logsRequests)
                .printHeaders(logsRequests)
                .build(OllamaServices.class);
    }

    /**
     * Constructs an instance of {@code Ollama} with a specified base URL, a default model, and authorization header.
     *
     * @param baseUrl          The base URL of the API service.
     * @param defaultModel     The default model to use for requests.
     * @param authorisationHeader The authorization header to include in requests.
     */
    public Ollama(String baseUrl, String defaultModel, Headers authorisationHeader) {
        this.logsResponses = Boolean.parseBoolean(ContextStore.get("ollama-response-logging", "false"));
        this.logsRequests = Boolean.parseBoolean(ContextStore.get("ollama-request-logging", "false"));
        this.readTimeout = Integer.parseInt(ContextStore.get("ollama-response-timeout", "1200"));
        this.defaultModel = defaultModel;
        keepLogs(logsRequests);
        ollamaServices = new WasapiClient.Builder()
                .baseUrl(baseUrl)
                .headers(authorisationHeader)
                .readTimeout(readTimeout)
                .logRequestBody(logsRequests)
                .printHeaders(logsRequests)
                .build(OllamaServices.class);
    }

    /**
     * Sends an inference request with a given message.
     *
     * @param prompt The {@code PromptModel} containing the prompt message.
     * @return A {@code ResponseModel} containing the API response.
     */
    public InferenceResponse inference(InferenceModel prompt) {
        prompt = new InferenceModel.Builder(prompt)
                .model(prompt.getModel() == null ? defaultModel : prompt.getModel())
                .build();
        log.info("Inference with " + prompt.getModel() + ".");
        Call<InferenceResponse> inferenceCall = ollamaServices.generate(prompt);
        return perform(inferenceCall, true, logsResponses, Response.class);
    }

    /**
     * Retrieves a list of available models from the Ollama server.
     *
     * @return A list of available models. The exact type of the returned object
     *         depends on the Ollama server's response format.
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
            log.info("Inference with " + prompt.getModel() + ".");
            prompt = new InferenceModel.Builder(prompt)
                    .model(prompt.getModel() == null ? defaultModel : prompt.getModel())
                    .format(responseType, requiredFields)
                    .build();
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

    /**
     * Gets the read timeout in milliseconds.  This timeout applies to network
     * operations when communicating with the Ollama server.
     *
     * @return The read timeout in milliseconds.
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the read timeout in milliseconds.  This timeout applies to network
     * operations when communicating with the Ollama server.
     *
     * @param readTimeout The read timeout in milliseconds.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
     * Checks if request logging is enabled.  If enabled, requests sent to the
     * Ollama server will be logged.
     *
     * @return True if request logging is enabled, false otherwise.
     */
    public boolean isLogsRequests() {
        return logsRequests;
    }

    /**
     * Enables or disables request logging.  When enabled, requests sent to the
     * Ollama server will be logged.
     *
     * @param logsRequests True to enable request logging, false to disable.
     */
    public void setLogsRequests(boolean logsRequests) {
        this.logsRequests = logsRequests;
    }

    /**
     * Checks if response logging is enabled.  If enabled, responses received from
     * the Ollama server will be logged.
     *
     * @return True if response logging is enabled, false otherwise.
     */
    public boolean isLogsResponses() {
        return logsResponses;
    }

    /**
     * Enables or disables response logging.  When enabled, responses received from
     * the Ollama server will be logged.
     *
     * @param logsResponses True to enable response logging, false to disable.
     */
    public void setLogsResponses(boolean logsResponses) {
        this.logsResponses = logsResponses;
    }

    /**
     * Gets the default model to use for requests.
     *
     * @return The default model name.
     */
    public String getDefaultModel() {
        return defaultModel;
    }

    /**
     * Sets the default model to use for requests.
     *
     * @param defaultModel The default model name.
     */
    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel;
    }

    /**
     * Gets the OllamaServices instance used for interacting with the Ollama server.
     *
     * @return The OllamaServices instance.
     */
    public OllamaServices getOllamaServices() {
        return ollamaServices;
    }

    /**
     * Sets the OllamaServices instance used for interacting with the Ollama server.
     *
     * @param ollamaServices The OllamaServices instance.
     */
    public void setOllamaServices(OllamaServices ollamaServices) {
        this.ollamaServices = ollamaServices;
    }
}