/**
 * PessimistPreset.java
 * <p>
 * Create an pessimistic agent using some defaults and a custom name & system prompt.
 */
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;

public class PessimistPreset extends DefaultModelPreset {

    /**
     * Constructor
     * Since we inherit some of the application defaults, we need
     * call the parent constructor.
     * @param   properties      Default language model settings
     */
    public PessimistPreset(LangChainDefaultsProperties properties) {
        super(properties);
    }

    @Override
    public String name() {
        return "Pessimist";
    }

    @Override
    public String systemPrompt() {
        return loadPrompt("prompts/system/pessimist.txt");
    }
}
