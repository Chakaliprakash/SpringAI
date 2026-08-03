package com.springAI.SpringAI.Controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestController {

    private final ChatClient ollamChatClient;
    private final ChatMemory chatMemory;

    public TestController(@Qualifier("ollamaChatClient") ChatClient ollamChatClient,
                          ChatMemory chatMemory) {
        this.ollamChatClient = ollamChatClient;
        this.chatMemory = chatMemory;
    }

    @Value("classpath:prompts/user-prompts")
    private Resource resource;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @GetMapping("/chat/memoryadvisor")
    public String getMemoryString(@RequestParam(defaultValue ="user1") String idString) {

        System.out.println(idString);

        return ollamChatClient.prompt()
                              .user(e->e.text(resource).param("topic", "java"))
                              .advisors(a->a.param(ChatMemory.CONVERSATION_ID,idString))
                              .call()
                            .content();  
    }

    @GetMapping("/chat/memory")
    public String getMethodName(@RequestParam String conversationId) {
        System.out.println(conversationId);
        return this.chatMemory.get(conversationId).toString();
    }
    



    @GetMapping("/test")
    public String test() {

        if (apiKey == null || apiKey.isBlank()) {
            return "API Key NOT Loaded";
        }

        String masked = apiKey.length() > 10
                ? apiKey.substring(0, 10) + "..."
                : "***";
        return "API Key Loaded: " + masked;
    }
    


}