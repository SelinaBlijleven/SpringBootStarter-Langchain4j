/**
 * ResearcherService.java
 * <p>
 * The Group Chat service lets us communicate with multiple LLMs, which
 * can all have their own settings and instructions.
 * @TODO: Implement the embedding store for RAG and create it when the application starts
 * @TODO: Look into the LangChainServiceFactory and pass the embeddingStore for the researcher to the langchain service.
 * @TODO: Implement the ResearcherController and test your endpoints.
 * Optional: Add more tools for the researcher and optimise its system prompt. (Located in the resources)
 */
package org.example.springbootstarterlangchain4j.services;

import dev.langchain4j.data.message.ChatMessage;
import org.example.springbootstarterlangchain4j.models.LangChainChatService;
import org.example.springbootstarterlangchain4j.models.LangChainServiceFactory;
import org.example.springbootstarterlangchain4j.models.presets.ResearcherPreset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResearcherService extends ModelService {

    ResearcherPreset preset;
    LangChainChatService langChainService;

    /**
     * Constructor
     * Create a ChatModelService with a given ChatModel
     */
    public ResearcherService(LangChainServiceFactory factory, ResearcherPreset preset) {
        this.preset = preset;

        // Create the models from the presets
        this.langChainService = factory.create(preset);
    }

    /**
     * Send a single message to our AI service, which
     * manages pretty much everything for us.
     *
     * @param   message         Prompt for the LLM
     * @return                  LLM answer
     */
    public String chat(String message) {
        return this.langChainService.chat(message);
    }

    /**
     * Answer a prompt based on an ongoing conversation
     *
     * @param chatMemory    Conversation history
     * @return              LLM Answer
     */
    public String chat(ArrayList<ChatMessage> chatMemory) {
        return "Chat continuation not implemented.";
    }
}
