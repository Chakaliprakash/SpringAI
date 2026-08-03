package com.springAI.SpringAI.Service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import com.springAI.SpringAI.Entity.Temp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TempService {

    private final ChatClient ollamaChatClient;

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
}