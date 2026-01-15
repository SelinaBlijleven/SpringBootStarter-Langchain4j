package org.example.springbootstarterlangchain4j.controllers;

import org.example.springbootstarterlangchain4j.services.ChatModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModelController {

    private final ChatModelService chatModelService;

    public ChatModelController(ChatModelService chatModelService) {
        this.chatModelService = chatModelService;
    }

    @GetMapping("/model")
    public String model(
            @RequestParam(value = "message", defaultValue = "Hello") String message
    ) {
        return chatModelService.chat(message);
    }
}
