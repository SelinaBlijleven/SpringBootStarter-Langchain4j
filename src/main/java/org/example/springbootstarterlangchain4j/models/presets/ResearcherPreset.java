/**
 * ResearcherPreset.java
 * <p>
 * Create a researcher agent using application defaults and a custom name and system prompt.
 */
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.springframework.stereotype.Component;

@Component
public class ResearcherPreset extends DefaultModelPreset {

    /**
     * Constructor
     * Since we inherit some of the application defaults, we need
     * call the parent constructor.
     * @param   properties      Default language model settings
     */
    public ResearcherPreset(LangChainDefaultsProperties properties) {
        super(properties);

        // Set the system prompt to use for the optimist.
        systemPrompt = loadPrompt("prompts/system/researcher.txt");
    }

    @Override
    public String name() {
        return "Researcher";
    }
}
