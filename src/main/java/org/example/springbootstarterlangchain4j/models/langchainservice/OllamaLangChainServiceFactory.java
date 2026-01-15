package org.example.springbootstarterlangchain4j.models.langchainservice;

import dev.langchain4j.service.AiServices;
import org.example.springbootstarterlangchain4j.models.LangChainChatService;
import org.example.springbootstarterlangchain4j.models.LangChainServiceFactory;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.example.springbootstarterlangchain4j.models.chatmodel.OllamaChatModelFactory;
import org.springframework.stereotype.Component;

@Component
public class OllamaLangChainServiceFactory implements LangChainServiceFactory {

    private final OllamaChatModelFactory modelFactory;

    /**
     * For the AI services, we also need to construct an OllamaChatModel,
     * which means we need access to the OllamaChatModelFactory and its defaults.
     *
     * @param factory   Autowire ChatModelFactory for Ollama
     */
    public OllamaLangChainServiceFactory(OllamaChatModelFactory factory) {
        this.modelFactory = factory;
    }

    /**
     * Create the AI service according to our presets.
     *
     * @param preset    Create the AI service
     * @return          AI service that follows the LangChainChatService interface
     */
    public LangChainChatService create(ModelPreset preset) {
        return AiServices.builder(LangChainChatService.class)
                .chatModel(modelFactory.create(preset))
                .systemMessageProvider(o -> preset.systemPrompt())
                .build();
    }
}
