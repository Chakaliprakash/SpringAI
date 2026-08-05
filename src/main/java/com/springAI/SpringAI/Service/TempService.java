package com.springAI.SpringAI.Service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.springAI.SpringAI.Entity.Temp;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class TempService {

    private final ChatClient ollamaChatClient;

        
    @Value("classpath:/prompts/user-prompts")
    private Resource promptResource;

    @Value("classpath:/prompts/system-prompts")
    private Resource systemResource;



    public Temp geminientity2List(String idString,String message) {

        Temp tutorList = ollamaChatClient.prompt()
                .user(message)
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        idString
                ))
                .call()
                .entity(Temp.class);

        System.out.println(tutorList);

        return tutorList;
    }


    public Temp getmemoryList(String idString,String message) {

        return ollamaChatClient.prompt()
                .user(message)
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        idString
                ))
                .call()
                .entity(Temp.class);
    }


    public Flux<String> Stream(String message) {

        return ollamaChatClient.prompt()
                .system(systemResource)
                .user(u -> u.text(promptResource).param("topic", message))
                .stream()
                .content();
    }



    
}