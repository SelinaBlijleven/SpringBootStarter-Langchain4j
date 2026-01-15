/**
 * GroupChatService.java
 * <p>
 * The Group Chat service lets us communicate with multiple LLMs, which
 * can all have their own settings and instructions.
 */
package org.example.springbootstarterlangchain4j.services;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import org.example.springbootstarterlangchain4j.models.ChatModelFactory;
import org.example.springbootstarterlangchain4j.models.ModelPreset;
import org.example.springbootstarterlangchain4j.models.presets.OptimistPreset;
import org.example.springbootstarterlangchain4j.models.presets.PessimistPreset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupChatService extends ModelService {

    // We could definitely improve this to also allow for >2 agents.
    private final ChatModel optimist;
    private final ChatModel pessimist;
    private final ModelPreset optimistPreset;
    private final ModelPreset pessimistPreset;

    /**
     * Constructor
     * Create a ChatModelService with a given ChatModel
     */
    public GroupChatService(ChatModelFactory factory, OptimistPreset optimistPreset, PessimistPreset pessimistPreset) {
        this.optimistPreset = optimistPreset;
        this.pessimistPreset = pessimistPreset;

        // Create the models from the presets
        this.optimist = factory.create(optimistPreset);
        this.pessimist = factory.create(pessimistPreset);
    }

    /**
     * Send a single message to the model of choice. Since
     * we are using the ChatModel, we have to manage our own history.
     *
     * @param   message         Prompt for the LLM
     * @return                  LLM answer
     */
    public String chat(String message) {
        // Create the SystemMessage objects
        SystemMessage optimistMsg = SystemMessage.from(optimistPreset.systemPrompt());
        SystemMessage pessimistMsg = SystemMessage.from(pessimistPreset.systemPrompt());

        // User message goes into shared memory
        ArrayList<ChatMessage> conversationHistory = new ArrayList<>();
        conversationHistory.add(UserMessage.from(message));

        // Ask the pessimist for their reply first
        AiMessage pessimistReply = pessimist.chat(
                withSystem(pessimistMsg, conversationHistory)
        ).aiMessage();

        // Store pessimist reply in conversation memory
        // Not every provider accepts an AIMessage as the last
        // message, so sadly we have to work around that.
        // We do not show it to the user :)
        conversationHistory.add(
                UserMessage.from("""
            De pessimist zei het volgende:

            "%s"

            Geef nu jouw input over de kwestie.
            """.formatted(pessimistReply.text()))
        );

        // Now we ask the optimist, who will be able to
        // read the pessimist's reply too! For the
        // last message, we use the AI reply, so the LLM
        // provider still understands that our agent did respond.
        AiMessage optimistReply = optimist.chat(
                withSystem(optimistMsg, conversationHistory)
        ).aiMessage();

        // We are actually returning two messages, which we
        // format to get a singular output for the endpoint.
        return """
            :( %s:
            %s

            :) %s:
            %s
            """.formatted(
                    pessimistPreset.name(),
                    pessimistReply.text(),
                    optimistPreset.name(),
                    optimistReply.text()
        );
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

    /**
     * Helper function to merge the conversation history with the system
     * prompt for a specific LLM instance.
     * @param system    System message for the LLM
     * @param history   Conversation history
     * @return          Merged history for LLM usage
     */
    private List<ChatMessage> withSystem(SystemMessage system, List<ChatMessage> history) {
        List<ChatMessage> result = new ArrayList<>(history.size() + 1);
        result.add(system);
        result.addAll(history);
        return result;
    }
}
