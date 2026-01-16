/**
 * MistralDefaultsProperties.java
 * <p>
 * Keeps configuration from application.properties specifically for
 * LLMs provided by Mistral
 */
package org.example.springbootstarterlangchain4j.config.providers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ConfigurationProperties(prefix = "mistral.defaults")
// Create a getter and setter for every property with Lombok
@Getter
@Setter
public class MistralDefaultsProperties {
    private String modelName;
    private String apiKey;
}

