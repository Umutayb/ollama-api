import context.ContextStore;
import models.Pet;
import okhttp3.Headers;
import ollama.Ollama;
import ollama.models.inference.InferenceModel;
import org.junit.Before;
import org.junit.Test;
import utils.Printer;
import utils.mapping.MappingUtilities;

/**
 * Unit test.
 */
public class AppTest {

    Printer log = new Printer(AppTest.class);
    String baseUrl;
    Ollama ollama;

    @Before
    public void before(){
        ContextStore.loadProperties("test.properties", "secret.properties");
        baseUrl = ContextStore.get("ollama-base-url");
        ollama = new Ollama(baseUrl, ContextStore.get("ollama-token").toString());
    }

    @Test
    public void dataGenerationTest() {
        InferenceModel prompt = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Create a pet. leave ID null")
                .build();
        Pet pet = ollama.inference(prompt, Pet.class);
        log.info(MappingUtilities.Json.getJsonStringFor(pet));;
    }
}
