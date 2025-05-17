package ollama.models.inference;

import utils.FileUtilities;

import java.util.List;

import static ollama.utilities.Utilities.getSchema;

/**
 * Represents a data model for sending prompt requests to an inference engine.
 * This model encapsulates the configuration details required for making an inference request, such as
 * the model name, prompt, stream option, and any associated images.
 *
 * <p> Example usage:
 * <pre>
 * InferenceModel model = new InferenceModel.Builder()
 *         .model("gemma3:27b")
 *         .prompt("Tell me a story.")
 *         .stream(true)
 *         .format("json")
 *         .build();
 * </pre>
 *
 * @author Umut Ay Bora
 * @version 0.0.1
 */
public class InferenceModel {
    /** The name of the model used for inference. */
    private final String model;

    /** The prompt to be sent for inference. */
    private final String prompt;

    /** Indicates whether the response should be streamed. */
    private final boolean stream;

    /** A list of images associated with the inference request, if any. */
    private final List<String> images;

    /** Temperature and seed options */
    private final Options options;

    /** The format of the response, which can be a JSON schema or other formats. */
    private final Object format;

    /**
     * Constructs a new InferenceModel with the provided configuration.
     *
     * @param builder The builder object containing the configuration details.
     */
    private InferenceModel(Builder builder) {
        this.model = builder.model;
        this.prompt = builder.prompt;
        this.stream = builder.stream;
        this.images = builder.images;
        this.format = builder.format;
        this.options = builder.options;
    }

    /**
     * Returns the model name.
     *
     * @return The model name as a String.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the prompt that is sent for inference.
     *
     * @return The prompt as a String.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Returns whether the response should be streamed.
     *
     * @return True if streaming is enabled, false otherwise.
     */
    public boolean isStream() {
        return stream;
    }

    /**
     * Returns the list of images associated with the request, if any.
     *
     * @return A list of image URLs or data.
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * Returns the temperature and seed options, if specified.
     *
     * @return Options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Returns the format of the response.
     *
     * @return The response format as an Object.
     */
    public Object getFormat() {
        return format;
    }

    public static class Options {
        double temperature;
        int seed;

        /**
         * Constructs an Options object with temperature and seed.
         *
         * @param temperature The temperature value.
         * @param seed The seed value.
         */
        public Options(double temperature, int seed) {
            this.temperature = temperature;
            this.seed = seed;
        }

        /**
         * Constructs an Options object with only a seed.
         *
         * @param seed The seed value.
         */
        public Options(int seed) {
            this.seed = seed;
        }

        /**
         * Constructs an Options object with only a temperature.
         *
         * @param temperature The temperature value.
         */
        public Options(double temperature) {
            this.temperature = temperature;
        }

        /**
         * Constructs an empty Options object.
         */
        public Options() {
        }

        /**
         * Returns the temperature.
         *
         * @return The temperature value.
         */
        public double getTemperature() {
            return temperature;
        }

        /**
         * Sets the temperature.
         *
         * @param temperature The temperature value to set.
         */
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        /**
         * Returns the seed.
         *
         * @return The seed value.
         */
        public int getSeed() {
            return seed;
        }

        /**
         * Sets the seed.
         *
         * @param seed The seed value to set.
         */
        public void setSeed(int seed) {
            this.seed = seed;
        }
    }

    /**
     * A builder class for creating InferenceModel objects.
     * <p>
     * This builder pattern allows for a flexible construction of the InferenceModel, where each of the
     * fields can be set independently. The builder ensures that the model and prompt are not null or empty before
     * constructing the model.
     * </p>
     */
    public static class Builder {
        private String model;
        private String prompt;
        private boolean stream = false;
        private List<String> images;
        private Options options;
        private Object format;

        /**
         * Default constructor for the Builder.
         */
        public Builder() {}

        /**
         * Constructs a builder out of an existing Inference model.
         *
         * @param model The model name.
         */
        public Builder(InferenceModel model) {
            this.model = model.getModel();
            this.prompt = model.getPrompt();
            this.stream = model.isStream();
            this.images = model.getImages();
            this.format = model.getFormat();
            this.options = model.getOptions();
        }

        /**
         * Sets the model name for the inference request.
         *
         * @param model The model name.
         * @return The builder instance for method chaining.
         */
        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets the prompt for the inference request.
         *
         * @param prompt The prompt.
         * @return The builder instance for method chaining.
         */
        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         * Enables or disables streaming for the response.
         *
         * @param stream True to enable streaming, false to disable.
         * @return The builder instance for method chaining.
         */
        public Builder stream(boolean stream) {
            this.stream = stream;
            return this;
        }

        /**
         * Sets the list of images to be associated with the inference request.
         *
         * @param images A list of image URLs or image data.
         * @return The builder instance for method chaining.
         */
        public Builder images(List<String> images) {
            this.images = images;
            return this;
        }

        /**
         * Sets the list of images to be associated with the inference request.
         *
         * @param filePath Path to the image that will be converted to Base64.
         * @return The builder instance for method chaining.
         */
        public Builder images(String filePath){
            this.images = List.of(FileUtilities.getEncodedString(filePath));
            return this;
        }

        /**
         * Sets the seed and temperature options to be associated with the inference request.
         *
         * @param options Path to the image that will be converted to Base64.
         * @return The builder instance for method chaining.
         */
        public Builder options(Options options){
            this.options = options;
            return this;
        }

        /**
         * Sets the seed and temperature options to be associated with the inference request.
         *
         * @param temperature Path to the image that will be converted to Base64.
         * @return The builder instance for method chaining.
         */
        public Builder temperature(double temperature){
            this.options = this.options == null ? new Options(temperature) : new Options(temperature, this.options.seed) ;
            return this;
        }

        /**
         * Sets the format of the response.
         *
         * @param format The response format, such as a JSON schema.
         *
         * <p>Example usage:</p>
         * <pre>
         * InferenceModel prompt = new InferenceModel.Builder(prompt)
         *     .model(prompt.getModel() == null ? defaultModel : prompt.getModel())
         *     .format(getSchema(responseType, requiredFields))
         *     .build();
         * </pre>
         *
         * @return The builder instance for method chaining.
         */
        public Builder format(Object format) {
            this.format = format;
            return this;
        }

        /**
         * Sets the format of the response.
         *
         * @param format The response format, such as JSON schema.
         * @return The builder instance for method chaining.
         */
        public <T> Builder format(Class<T> format, String... requiredFields) {
            this.format = getSchema(format, requiredFields);
            return this;
        }

        /**
         * Builds and returns a new InferenceModel object.
         *
         * @return A new InferenceModel object.
         * @throws IllegalArgumentException if the model or prompt is null or empty.
         */
        public InferenceModel build() {
            if (model == null || model.isEmpty())
                throw new IllegalArgumentException("Model name cannot be null or empty.");

            if (prompt == null || prompt.isEmpty())
                throw new IllegalArgumentException("Prompt cannot be null or empty.");

            return new InferenceModel(this);
        }
    }
}
