/**
 * ResearcherController.java
 * <p>
 * Controller for the researcher end point
 */
package org.example.springbootstarterlangchain4j.controllers;

import org.example.springbootstarterlangchain4j.services.ResearcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResearcherController {

    private final ResearcherService researcherService;

    public ResearcherController(ResearcherService researcherService) {
        this.researcherService = researcherService;
    }

    @GetMapping("/research")
    public String model(
            @RequestParam(value = "message", defaultValue = "How do batteries work?") String message
    ) {
        return researcherService.chat(message);
    }
}
