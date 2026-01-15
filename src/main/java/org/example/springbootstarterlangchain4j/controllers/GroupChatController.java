package org.example.springbootstarterlangchain4j.controllers;

import org.example.springbootstarterlangchain4j.services.GroupChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupChatController {

    private final GroupChatService groupChatService;

    public GroupChatController(GroupChatService groupChatService) {
        this.groupChatService = groupChatService;
    }

    @GetMapping("/groupchat")
    public String groupchat(
            @RequestParam(
                    value = "message",
                    defaultValue = "I want to build a rocket to go to mars.") String message
    ) {
        return groupChatService.chat(message);
    }
}
