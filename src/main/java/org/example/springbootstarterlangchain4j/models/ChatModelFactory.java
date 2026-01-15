/**
 * ChatModelFactory.java
 * <p>
 * Each ChatModelFactory has the singular responsibility of creating a model
 * from a preset. We can implement it using different providers, like Ollama or
 * through an API. This interface helps make sure that we can create different
 * types of ChatModels in a unified way, which might help us switch models when
 * necessary later!
 * <p>
 * https://refactoring.guru/design-patterns/factory-method
 */
package org.example.springbootstarterlangchain4j.models;

import dev.langchain4j.model.chat.ChatModel;

public interface ChatModelFactory {

    // Each type of provider needs to use this method signature to
    // create their specific implementation of a chat model. This
    // is our factory method.
    ChatModel create(ModelPreset preset);
}

