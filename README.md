# Ollama API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.umutayb/ollama-api?color=brightgreen&label=OllamaAPI)](https://mvnrepository.com/artifact/io.github.umutayb/ollama-api/latest)

## Overview
Ollama API is a small set of utilities to make integrating LLM's (via ollama) into your Java projects easy.

## Features
- API request handling with **Retrofit**.
- JSON schema generation using **Jackson**.
- Customizable request formatting and logging.
- Seamless mapping of JSON responses to Java objects.
- Automatic JSON schema generation to format LLM responses for seamless integration of LLMs into Java projects.

### Installation

Include the required dependencies in your project:
```xml
<dependencies>
    <!-- Ollama API -->
    <dependency>
        <groupId>io.github.umutayb</groupId>
        <artifactId>ollama-api</artifactId>
        <version>0.0.1</version>
    </dependency>
</dependencies>
```

## Usage

### Initialize Ollama class
```java
Ollama ollama = new Ollama("http://localhost:11435/");
```

### Perform API Inference
```java
PromptModel prompt = new PromptModel();
prompt.setModel("qwen2.5:32b");
ResponseModel response = ollama.inference(prompt);
System.out.println(response.getResponse());
```

### Custom Response Mapping
```java
MyResponseType customResponse = ollama.inference(prompt, MyResponseType.class, "field1", "field2");
```

### Generate JSON Schema
```java
JsonNode schema = Ollama.getSchema(MyClass.class, "requiredField1", "requiredField2");
System.out.println(schema.toPrettyString());
```

## JSON Schema for LLM Integration
Ollama provides **automatic JSON schema generation** to structure responses from Large Language Models (LLMs). This ensures that responses conform to expected formats, making integration of LLMs into Java projects seamless.

### How It Works
- The library dynamically generates a **JSON schema** for any given Java class.
- Developers can specify **required fields** to enforce structured responses.
- The schema is used to **format LLM responses**, ensuring they map directly to Java objects.

### Example:
```java
JsonNode schema = Ollama.getSchema(MyClass.class, "name", "age");
System.out.println(schema.toPrettyString());
```
This allows for precise control over API responses and smooth integration into existing Java applications.

## License
This project is licensed under the **MIT License**.

## Author
Developed by **Umut Ay Bora**.

