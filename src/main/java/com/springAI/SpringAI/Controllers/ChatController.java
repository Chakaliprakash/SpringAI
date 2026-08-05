package com.springAI.SpringAI.Controllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
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
import reactor.core.publisher.Flux;

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

    @GetMapping("/mutate")
    public String getMethodName(@RequestParam String message) {
        return chatService.gemini2String(message);
    }
    
    private static final String str = """
You are a coding expert and software engineer.

Always address e as {name}.

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

    @GetMapping("/template2")
    public String temnplString2() {
        return template.resourcePrompts();
    }
    

    private final MessageChatMemoryAdvisor memoryAdvisor;

    
    @GetMapping("/chat/memoryadvisor2/mem")
    public Flux<String> getMemoryString3(@RequestParam(defaultValue = "1") String idString,@RequestParam String message) {

        System.out.println(idString);

        return ollamaChatClient.prompt(new Prompt(List.of(new UserMessage(message))))
                            //   .user(e->e.text(resource).param("topic", "Prakash Chakali"))
                            .advisors(memoryAdvisor)
                            .advisors(a->a.param(ChatMemory.CONVERSATION_ID, idString))
                            .stream()
                            .content();  
    }
}