package com.springAI.SpringAI.Configuration;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
// import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springAI.SpringAI.Advisors.TokenCount;

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
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
        .maxMessages(10)
        .build();
    }

    @Bean
    public ChatClient ollamaChatClient(
            @Qualifier("ollamaChatModel") ChatModel model,
            ChatMemory chatMemory) {

        return ChatClient.builder(model)
                .defaultSystem(tone)
                // .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultAdvisors(new TokenCount(),new SafeGuardAdvisor(List.of("Game")))
                .defaultOptions(
                        OllamaChatOptions.builder()
                                .model("codellama")
                                .temperature(0.2))
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