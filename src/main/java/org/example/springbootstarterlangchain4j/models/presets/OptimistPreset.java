/**
 * OptimistPreset.java
 * <p>
 * Create an optimistic agent using application defaults and a custom name & system prompt.
 */
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;

public class OptimistPreset extends DefaultModelPreset {

    /**
     * Constructor
     * Since we inherit some of the application defaults, we need
     * call the parent constructor.
     * @param   properties      Default language model settings
     */
    public OptimistPreset(LangChainDefaultsProperties properties) {
        super(properties);
    }

    @Override
    public String name() {
        return "Optimist";
    }

    @Override
    public String systemPrompt() {
        return loadPrompt("prompts/system/optimist.txt");
    }
}
