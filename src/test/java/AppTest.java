import com.fasterxml.jackson.core.JsonProcessingException;
import context.ContextStore;
import models.Pet;
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

    @Test
    public void generationTest() throws JsonProcessingException {
        ContextStore.loadProperties("test.properties");
        Ollama ollama = new Ollama("http://i-bora.com:11435/");
        InferenceModel prompt = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Create a pet. leave ID null")
                .build();
        Pet pet = ollama.inference(prompt, Pet.class);
        log.info(MappingUtilities.Json.getJsonString(pet));;
    }
}
