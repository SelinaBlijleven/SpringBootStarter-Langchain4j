/**
 * ChatModelService.java
 * <p>
 * The service layer allows us to specify different services for our endpoints.
 * This is the most basic version of a service: we only use one ChatModel, which is
 * the application default. The settings for it are in application.properties.
 * This service has a corresponding Controller in ChatModelController, which is in the
 * presentation layer of the application and handles input/output.
 */
package org.example.springbootstarterlangchain4j.services;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatModelService implements ChatModelService {

    private final ChatModel chatModel;

    /**
     * Constructor
     * Create a ChatModelService with a given ChatModel
     */
    public OllamaChatModelService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * Send a single message to the model of choice
     *
     * @param   message         Prompt for the LLM
     * @return                  LLM answer
     */
    public String chat(String message) {
        return chatModel.chat(message);
    }

    /**
     * Answer a prompt based on an ongoing conversation
     *
     * @param chatMemory    Chat history
     * @return              LLM Answer
     */
    public ChatResponse chat(ChatMemory chatMemory) {
        return chatModel.chat(chatMemory.messages());
    }
}
