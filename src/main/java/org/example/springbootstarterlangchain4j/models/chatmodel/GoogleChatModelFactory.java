/**
 * GoogleChatModelFactory.java
 * <p>
 * ChatModel factory using Google AI Studio (Gemini Developer API) via LangChain4j.
 * <p>
 * Expects an API key from Google AI Studio (Gemini API key) in your environment variables.
 */
package org.example.springbootstarterlangchain4j.models.chatmodel;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.example.springbootstarterlangchain4j.config.providers.GoogleDefaultsProperties;
import org.example.springbootstarterlangchain4j.models.ChatModelFactory;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GoogleChatModelFactory implements ChatModelFactory {

    private final GoogleDefaultsProperties providerDefaults;
    private final LangChainDefaultsProperties defaults;

    public GoogleChatModelFactory(
            GoogleDefaultsProperties providerDefaults,
            LangChainDefaultsProperties defaults
    ) {
        this.providerDefaults = providerDefaults;
        this.defaults = defaults;
    }

    @Override
    public ChatModel create(ModelPreset modelPreset) {

        // Check if we have a valid API key for Google AI Studio (Gemini API)
        if (providerDefaults.getApiKey() == null
                || providerDefaults.getApiKey().isBlank()
                || providerDefaults.getApiKey().equals("${GOOGLE_AI_API_KEY}")) {
            throw new IllegalStateException(
                    "GOOGLE_AI_API_KEY not set. Please add it to the environment variables."
            );
        }

        // Create a Gemini chat model (Google AI Studio / Gemini Developer API)
        return GoogleAiGeminiChatModel.builder()
                .apiKey(providerDefaults.getApiKey())
                .modelName(providerDefaults.getModelName())
                .temperature(defaults.getTemperature())
                .build();
    }
}
