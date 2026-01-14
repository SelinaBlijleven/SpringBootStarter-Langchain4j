package org.example.springbootstarterlangchain4j.controllers;

import org.example.springbootstarterlangchain4j.services.OllamaChatModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModelController {

    private final OllamaChatModelService ollamaChatModelService;

    public ChatModelController(OllamaChatModelService ollamaChatModelService) {
        this.ollamaChatModelService = ollamaChatModelService;
    }

    @GetMapping("/model")
    public String model(
            @RequestParam(value = "message", defaultValue = "Hello") String message
    ) {
        return ollamaChatModelService.chat(message);
    }
}
