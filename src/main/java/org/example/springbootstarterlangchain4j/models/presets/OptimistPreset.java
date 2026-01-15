/**
 * OptimistPreset.java
 * <p>
 * Create an optimistic agent using application defaults and a custom name & system prompt.
 * This preset can be used to create a chat model with this role using
 * any ChatModelFactory.
 */
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.springframework.stereotype.Component;

@Component
public class OptimistPreset extends DefaultModelPreset {

    /**
     * Constructor
     * Since we inherit some of the application defaults, we need
     * call the parent constructor.
     * @param   properties      Default language model settings
     */
    public OptimistPreset(LangChainDefaultsProperties properties) {
        super(properties);

        // Set the system prompt to use for the optimist.
        systemPrompt = loadPrompt("prompts/system/optimist.txt");
    }

    @Override
    public String name() {
        return "Optimist";
    }
}
