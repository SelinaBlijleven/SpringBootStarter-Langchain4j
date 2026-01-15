/**
 * LangChainChatService.java
 * <p>
 * This interface specifies what functionality our AI
 * service should hold. We will just create a simple
 * chat endpoint that returns a string.
 */
package org.example.springbootstarterlangchain4j.models;

import dev.langchain4j.service.UserMessage;

public interface LangChainChatService {

    String chat(@UserMessage String request);
}
