/**
 * OllamaDefaultsProperties.java
 * <p>
 * Default properties for Ollama models, using a specific base URL and
 * Ollama-compatible model.
 */
package org.example.springbootstarterlangchain4j.config.providers;

import lombok.Getter;
import lombok.Setter;
import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// Application.properties has these prefixed for clarity
@ConfigurationProperties(prefix = "ollama.defaults")
// Create getters and setters for the properties
@Getter
@Setter
public class OllamaDefaultsProperties extends LangChainDefaultsProperties {
    String modelName;
    String baseUrl;
}
