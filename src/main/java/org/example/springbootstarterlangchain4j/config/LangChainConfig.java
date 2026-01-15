/**
 * LangChainConfig.java
 * <p>
 * This class provides some Spring Beans for the application configuration.
 * We need specific objects in our services, like models made from presets.
 * The services pull from this configuration when needed.
 */
package org.example.springbootstarterlangchain4j.config;

import dev.langchain4j.model.chat.ChatModel;
import org.example.springbootstarterlangchain4j.models.ChatModelFactory;
import org.example.springbootstarterlangchain4j.models.presets.DefaultModelPreset;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainConfig {

    /**
     * Create a model preset from application defaults; this is used
     * for constructing the model later.
     */
    @Bean
    public ModelPreset defaultModelPreset(LangChainDefaultsProperties properties) {
        return new DefaultModelPreset(properties);
    }

    /**
     * Return a default model based on the application default settings.
     * Since we have implemented a ChatModel factory interface, we can
     * treat each provider the same.
     */
    @Bean
    ChatModel defaultModel(ChatModelFactory factory, ModelPreset preset) {
        // Use the ChatModel factory to create the default model
        return factory.create(preset);
    }
}
