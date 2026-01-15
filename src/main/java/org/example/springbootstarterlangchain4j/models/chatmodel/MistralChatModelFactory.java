/**
 * MistralChatModelFactory.java
 * <p>
 * ChatModel factory using the Mistral API, which can be
 * used for free to experiment. Please be mindful of the number
 * of requests you send and the model used.
 */
package org.example.springbootstarterlangchain4j.models.chatmodel;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import org.example.springbootstarterlangchain4j.config.providers.MistralDefaultsProperties;
import org.example.springbootstarterlangchain4j.models.ChatModelFactory;
import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MistralChatModelFactory implements ChatModelFactory {

    private final MistralDefaultsProperties providerDefaults;
    private final LangChainDefaultsProperties defaults;

    public MistralChatModelFactory(
            MistralDefaultsProperties providerDefaults,
            LangChainDefaultsProperties defaults
    ) {
        this.providerDefaults = providerDefaults;
        this.defaults = defaults;
    }

    @Override
    public ChatModel create(ModelPreset modelPreset) {
        // Check if we have a valid API key for Mistral AI
        if (providerDefaults.getApiKey() == null ||
                providerDefaults.getApiKey().equals("${MISTRAL_API_KEY}")) {
            throw new IllegalStateException(
                    "MISTRAL_API_KEY not set. Please add it to the environment variables."
            );
        }

        // We use the Mistral API yay
        return MistralAiChatModel.builder()
                .apiKey(providerDefaults.getApiKey())
                .modelName(providerDefaults.getModelName())
                .temperature(defaults.getTemperature())
                .build();
    }
}
