package com.springAI.SpringAI.Controllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springAI.SpringAI.Entity.Tut;
import com.springAI.SpringAI.Service.ChatService;
import com.springAI.SpringAI.Service.Template;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private ChatService chatService;

    private Template template;

    @Qualifier("openAiChatClient")
    private ChatClient openAiChatClient;

    @Qualifier("ollamaChatClient")
    private ChatClient ollamaChatClient;

    @Qualifier("geminiChatClient")
    private ChatClient geminiChatClient;

    @GetMapping("/openai")
    public String openai(@RequestParam String message) {
        return openAiChatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/ollama")
    public String ollama(@RequestParam String message) {
        System.out.println("Message is : "+message);
        String response= ollamaChatClient.prompt()
                .user(message)
                .call()
                .content();
                return response;
    }

    @GetMapping("/gemini")
    public String gemini(@RequestParam String message) {
        System.err.println("Message is : "+message);
        String response;
        try {
            response= geminiChatClient.prompt()
                    .user(message)
                    .call()
                    .content();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            return "Error generating response";
        }
        return response;
    }

    @GetMapping("/gemini2")
    public ResponseEntity<List<Tut>> gemini2(@RequestParam String message) {
        System.out.println("Message is : "+message);
        return ResponseEntity.ok(chatService.geminientity(message));
    }

    @GetMapping("/path")
    public String getMethodName(@RequestParam String message) {
        return chatService.gemini2String(message);
    }
    
    private static final String str = """
You are a coding expert and software engineer.

Always address me as {name}.

User question:
{message}
""";

    @GetMapping("/dummy")
    public String Dummy(@RequestParam String message) {
        System.out.println("Message is : "+message);
        String response= ollamaChatClient.prompt()
                .user(e->e.text(str).param("message", message).param("name", "Prakash Chakali"))
                .call()
                .content();
                System.out.println(response);
                return response;
    }

    @GetMapping("/template")
    public String temnplString() {
        return template.promptTemplate();
    }
    
}