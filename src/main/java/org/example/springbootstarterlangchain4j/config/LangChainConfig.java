/**
 * LangChainConfig.java
 * <p>
 * This class provides some Spring Beans for the application configuration.
 * We need specific objects in our services, like models made from presets.
 * The services pull from this configuration when needed.
 */
package org.example.springbootstarterlangchain4j.config;

import dev.langchain4j.model.chat.ChatModel;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.example.springbootstarterlangchain4j.models.chatmodel.MistralChatModelFactory;
import org.example.springbootstarterlangchain4j.models.chatmodel.OllamaChatModelFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainConfig {

    @Bean
    @ConditionalOnProperty(
            name = "langchain4j.defaults.provider",
            havingValue = "ollama"
    )
    public ChatModel ollamaModel(OllamaChatModelFactory factory, ModelPreset preset) {
        return factory.create(preset);
    }

    @Bean
    @ConditionalOnProperty(
            name = "langchain4j.defaults.provider",
            havingValue = "mistral"
    )
    public ChatModel mistralModel(MistralChatModelFactory factory, ModelPreset preset) {
        return factory.create(preset);
    }
}
