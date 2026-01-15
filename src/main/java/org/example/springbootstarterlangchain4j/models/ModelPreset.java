/**
 * ModelPreset.java
 * <p>
 * We create several model presets to easily work with different LLMs,
 * who might have different tasks and parameters. This class contains
 * the information we need for each model.
 */
package org.example.springbootstarterlangchain4j.models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public interface ModelPreset {

    String name();

    String baseUrl();

    String systemPrompt();

    String modelName();

    double temperature();

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
