package com.springAI.SpringAI.Configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class chatConfig {

    private static final String tone = """
            You are a helpful AI assistant.
            Always answer in exactly one sentence.
            Do not use bullet points, numbered lists, markdown, or line breaks.
            Keep every response under 30 words.
            """;
    @Bean
    public ChatClient openAiChatClient(
            @Qualifier("openAiChatModel") ChatModel model) {
                System.out.println(model.getClass().getName());

            return ChatClient.builder(model)
            .defaultSystem(tone)                    
            .build();
    }

    @Bean
    public ChatClient ollamaChatClient(
            @Qualifier("ollamaChatModel") ChatModel model) {
        System.out.println(model.getClass().getName());
        return ChatClient.builder(model)
        .defaultOptions(OllamaChatOptions.builder().temperature(0.2))
        .defaultSystem(tone) 
        .build();
    }

    @Bean
    public ChatClient geminiChatClient(@Qualifier("googleGenAiChatModel") ChatModel model) {
        System.out.println(model.getClass().getName());
        return ChatClient.builder(model)
        .defaultSystem(tone)
        .build();
    }

}