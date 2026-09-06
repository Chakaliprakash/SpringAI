package com.springAI.SpringAI.Controllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springAI.SpringAI.Entity.Temp;
import com.springAI.SpringAI.Service.TempService;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestController {

    private final ChatClient ollamChatClient;
    private final ChatMemory chatMemory;
    private final TempService tempService;

    public TestController(@Qualifier("ollamaChatClient") ChatClient ollamChatClient,
                          ChatMemory chatMemory,TempService tempService) {
        this.ollamChatClient = ollamChatClient;
        this.chatMemory = chatMemory;
        this.tempService=tempService;
    }

    @Value("classpath:/prompts/user-prompts")
    private Resource promptResource;

    @Value("classpath:/prompts/system-prompts")
    private Resource systemResource;

    @Value("${spring.ai.openai.api-key:}")
    private String apiKey;

    @GetMapping("/chat/memoryadvisor")
    public String getMemoryString(@RequestParam(defaultValue = "1") String idString) {

        System.out.println(idString);

        return ollamChatClient.prompt()
                            //   .user(e->e.text(resource).param("topic", "Prakash Chakali"))
                            .user("Im Prakash Chakali and Hi, my name is Rahul Sharma. I am 28 years old. I live in Hyderabad. I work as a software developer and I enjoy playing cricket in my free time. ")
                            .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                            .advisors(a->a.param(ChatMemory.CONVERSATION_ID, idString))
                            .call()
                            .content();  
    }

    @GetMapping("/chat/memory")
    public String getMethodName(@RequestParam String idString, @RequestParam String meString) {
        System.out.println(idString + " " + meString);


        MessageChatMemoryAdvisor advisor =
            MessageChatMemoryAdvisor.builder(chatMemory)
                    .build();

        String str = ollamChatClient.prompt(new Prompt(List.of(new UserMessage(meString))))
                .advisors(advisor)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID, idString))
                .call()
                .content();
        return str;
    }

    @GetMapping("/chat/Temp")
    public Temp getMethodName2String(@RequestParam String idString,@RequestParam String message) {
        return tempService.geminientity2List(idString,message );
    }

    @GetMapping("/chat/Temp2")
    public Temp getMethodName3String(@RequestParam String idString, @RequestParam String message) {
        return tempService.getmemoryList(idString, message);
    }
    

    @GetMapping("/chat/logs")
    public String getMemoryString2() {
        return ollamChatClient.prompt()
                            //   .user(e->e.text(resource).param("topic", "Prakash Chakali"))
                            .user("My name is Prakash Chakali ,Just Greet me using emojies...")
                            .advisors(new SimpleLoggerAdvisor())
                            .call()
                            .content();  
    }




    @GetMapping("/chat/stream")
    public ResponseEntity<Flux<String>> stream(@RequestParam("message") String message) {
        return ResponseEntity.ok(tempService.Stream(message));
    }
    

    @GetMapping("/chat/streamtest")
    public Flux<String> Stream(String message) {

        return ollamChatClient.prompt()
                .system(systemResource)
                .user(u -> u.text(promptResource).param("topic", message))
                .stream()
                .chatResponse()
                .doOnNext(response -> System.out.println(response.getMetadata().getUsage()))
                .map(response -> response.getResult().getOutput().getText());
    }

    
}