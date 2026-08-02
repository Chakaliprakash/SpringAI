package com.springAI.SpringAI.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @GetMapping("/test")
    public String test() {

        if (apiKey == null || apiKey.isBlank()) {
            return "API Key NOT Loaded";
        }

        return "API Key Loaded: " + apiKey;
    }
}