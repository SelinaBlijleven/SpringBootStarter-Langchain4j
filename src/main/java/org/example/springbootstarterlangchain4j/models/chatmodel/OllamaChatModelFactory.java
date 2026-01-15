/**
 * OllamaChatModelFactory.java
 * <p>
 * This implementation of a ChatModelFactory uses Ollama to communicate
 * with a chat model. It is an example of a concrete creator for the
 * factory pattern (also see ChatModelFactory).
 */
package org.example.springbootstarterlangchain4j.models.chatmodel;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.example.springbootstarterlangchain4j.models.ChatModelFactory;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.springframework.stereotype.Component;

@Component
public class OllamaChatModelFactory implements ChatModelFactory {

    /**
     * The create method for Ollama uses the OllamaChatModel,
     * which is one of many ChatModel implementations.
     *
     * @param       preset  Model presets
     * @return              ChatModel using Ollama
     */
    @Override
    public ChatModel create(ModelPreset preset) {
        return OllamaChatModel.builder()
                .modelName(preset.modelName())
                .temperature(preset.temperature())
                .build();
    }
}

