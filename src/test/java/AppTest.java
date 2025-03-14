import com.fasterxml.jackson.core.JsonProcessingException;
import context.ContextStore;
import ollama.Ollama;
import ollama.models.Prompt;
import org.junit.Test;
import utils.mapping.MappingUtilities;
import utils.reflection.ReflectionUtilities;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void generationTest() throws JsonProcessingException {
        ContextStore.loadProperties("test.properties");
        Ollama ollama = new Ollama("http://i-bora.com:11435/");
        Prompt prompt = new Prompt(
                "qwen2.5:32b",
                "Create a pet. leave ID null",
                false
        );
        Pet pet = ollama.inference(prompt, Pet.class);
        System.out.println(MappingUtilities.Json.getJsonString(pet));;
    }

    public static class Pet {
        Long id;
        DataModel category;
        String name;
        List<String> photoUrls;
        List<DataModel> tags;
        String status;

        public static class DataModel {
            Long id;
            String name;
        }
    }
}
