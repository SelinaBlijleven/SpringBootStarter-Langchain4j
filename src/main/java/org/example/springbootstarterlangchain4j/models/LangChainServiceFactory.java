/**
 * LangChainServiceFactory.java
 * <p>
 * The AIService primitive of LangChain4j helps us create
 * more intelligent systems, that go beyond the LLM. With this
 * building block of the framework, we can build services that
 * include RAG and more.
 * <p>
 * https://refactoring.guru/design-patterns/factory-method
 */
package org.example.springbootstarterlangchain4j.models;

public interface LangChainServiceFactory {

    // Each type of provider needs to use this method signature to
    // create their specific implementation of a chat model. This
    // is our factory method.
    LangChainChatService create(ModelPreset preset);
}

