/**
 * ModelPreset.java
 * <p>
 * We create several model presets to easily work with different LLMs,
 * who might have different tasks and parameters. This class contains
 * the information we need for every model. More specific set-up for
 * different providers can be found in the 'config' folder.
 */
package org.example.springbootstarterlangchain4j.models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public interface ModelPreset {

    // Each LLM is given a name/role to identify it by
    String name();

    // Each LLM needs a system prompt specific to its personality/role
    String systemPrompt();

    // The temperature we want to use for the model
    double temperature();

    // Helper method to load the prompt from .txt
    default String loadPrompt(String resourcePath) {
        try {
            return Files.readString(
                    Path.of(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource(resourcePath)).toURI())
            );
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load prompt: " + resourcePath, e);
        }
    }
}
