package ollama;

import ollama.models.chat.ChatModel;
import ollama.models.chat.ChatResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ollama.models.inference.InferenceModel;
import ollama.models.inference.InferenceResponse;

/**
 * Service interface for interacting with the Ollama API.
 */
public interface OllamaServices {

    /** Base URL for the API. */
    String BASE_URL = "";

    /**
     * Sends a POST request to generate a response based on the provided prompt.
     *
     * @param requestBody The request body containing the prompt model.
     * @return A call object containing the API response.
     */
    @POST("api/generate")
    Call<InferenceResponse> generate(@Body InferenceModel requestBody);

    /**
     * Sends a POST request to generate a response based on the provided prompt.
     *
     * @param requestBody The request body containing the prompt model.
     * @return A call object containing the API response.
     */
    @POST("api/chat/completions")
    Call<ChatResponse> chat(@Body ChatModel requestBody);

    /**
     * Retrieves the list of available models from the API.
     *
     * @return A call object containing the list of models.
     */
    @GET("api/models")
    Call<Object> getModels();
}
