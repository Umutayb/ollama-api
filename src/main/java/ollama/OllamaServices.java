package ollama;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ollama.models.PromptModel;
import ollama.models.ResponseModel;

public interface OllamaServices {
    
    String BASE_URL = "";

    @POST("api/generate")
    Call<ResponseModel> postMessage(@Body PromptModel requestBody);

    @GET("api/models")
    Call<Object> getModels();
}
