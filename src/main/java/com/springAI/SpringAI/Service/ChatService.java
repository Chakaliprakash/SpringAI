package com.springAI.SpringAI.Service;


import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.springAI.SpringAI.Entity.Tut;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ChatService {

    @Qualifier("geminiChatClient")
    private final ChatClient geminiChatClient;
    
    private ChatClient gemini2ChatClient() {
        System.out.println("This request based on Gemini mutate() Gemini2ChatClient");
        return geminiChatClient.mutate()
                .defaultOptions(GoogleGenAiChatOptions.builder()
                        .temperature(0.2)
                        .maxTokens(100))
                .build();
    }

    public List<Tut> geminientity(String message) {
        List<Tut> tutorList= geminiChatClient.prompt()
                            .user(message)
                            .call()
                            .entity(new ParameterizedTypeReference<List<Tut>>() {
                            });
                            System.out.println(tutorList);
                            return tutorList;
    }
    public String gemini2String(String message) {
        String response;
        try {
            response = gemini2ChatClient().prompt()
                    .user(message)
                    .call()
                    .content();
            return response;
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            return "Error generating response";
        }
    }
}
