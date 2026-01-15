
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.example.springbootstarterlangchain4j.models.ModelPreset;

public class DefaultModelPreset implements ModelPreset {

    private final LangChainDefaultsProperties properties;
    String systemPrompt;

    public DefaultModelPreset(LangChainDefaultsProperties properties) {
        this.properties = properties;
        // We will load the system prompt when we need it,
        // since our child classes can overwrite it.
        this.systemPrompt = "";
    }

    @Override
    public String name() {
        return "DefaultModel";
    }

    @Override
    public double temperature() {
        return properties.getTemperature();
    }

    @Override
    public String systemPrompt() {
        // Load the default system prompt only if we haven't set one yet
        if (systemPrompt.isEmpty()) {
            systemPrompt = loadPrompt("prompts/system/default.txt");
        }
        return systemPrompt;
    }
}
