import com.fasterxml.jackson.core.JsonProcessingException;
import context.ContextStore;
import models.Pet;
import okhttp3.Headers;
import ollama.Ollama;
import ollama.models.inference.InferenceModel;
import org.junit.Test;
import utils.Printer;
import utils.mapping.MappingUtilities;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    Printer log = new Printer(AppTest.class);
    String ollamaToken = "sk-88c6492c98af436f8de8b40182f63c8a";

    @Test
    public void generationTest() throws JsonProcessingException {
        ContextStore.loadProperties("test.properties");
        Ollama ollama = new Ollama(
                "https://i-bora.com/ollama/",
                Headers.of("Authorization", "Bearer " + ollamaToken));
        InferenceModel prompt = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Create a pet. leave ID null")
                .build();
        Pet pet = ollama.inference(prompt, Pet.class);
        log.info(MappingUtilities.Json.getJsonString(pet));;
    }
}
