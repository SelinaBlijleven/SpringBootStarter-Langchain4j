/**
 * LangChainDefaultsProperties.java
 * <p>
 * A config class to manage the default settings for the language model,
 * taken from the application.properties.
 */
package org.example.springbootstarterlangchain4j.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// Our configuration properties all have a prefix for their name,
// for easy identification
@ConfigurationProperties(prefix = "langchain4j.defaults")
// Generate getters for all properties in the class
@Getter
// Generate setters for all properties in the class
@Setter
public class LangChainDefaultsProperties {

    private String name = "ApplicationDefaultModel";
    private String baseUrl;
    private String modelName;
    private double temperature;
    private String systemPrompt;
}
