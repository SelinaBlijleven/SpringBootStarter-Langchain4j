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

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.springframework.stereotype.Service;

@Service
public class ChatModelService extends ModelService {

    private final ChatModel chatModel;
    private final ModelPreset preset;

    /**
     * Constructor
     * Create a ChatModelService with a given ChatModel
     */
    public ChatModelService(ChatModel chatModel, ModelPreset modelPreset) {
        this.chatModel = chatModel;
        this.preset = modelPreset;
    }

    /**
     * Send a single message to the model of choice. Since
     * we are using the ChatModel, we have to manage our own history.
     *
     * @param   message         Prompt for the LLM
     * @return                  LLM answer
     */
    public String chat(String message) {
        // Create the chat memory
        ChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);

        // Add the system message first, then the prompt
        memory.add(SystemMessage.from(preset.systemPrompt()));
        memory.add(UserMessage.from(message));

        // Pass the chat request on with the new message history
        return this.chat(memory).aiMessage().text();
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
