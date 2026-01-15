
package org.example.springbootstarterlangchain4j.models.presets;

import org.example.springbootstarterlangchain4j.config.LangChainDefaultsProperties;
import org.example.springbootstarterlangchain4j.models.ModelPreset;

public class DefaultModelPreset implements ModelPreset {

    private final LangChainDefaultsProperties properties;

    public DefaultModelPreset(LangChainDefaultsProperties properties) {
        this.properties = properties;
    }

    @Override
    public String name() {
        return "DefaultModel";
    }

    public String baseUrl() {
        return properties.getBaseUrl();
    }

    @Override
    public String modelName() {
        return properties.getModelName();
    }

    @Override
    public double temperature() {
        return properties.getTemperature();
    }

    @Override
    public String systemPrompt() {
        return loadPrompt(properties.getSystemPrompt());
    }
}
