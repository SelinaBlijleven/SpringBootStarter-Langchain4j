package org.example.springbootstarterlangchain4j.services;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;

public interface ChatModelService {

    public String chat(String message);

    public ChatResponse chat(ChatMemory chatMemory);
}
