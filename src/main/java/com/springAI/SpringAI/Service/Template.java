package com.springAI.SpringAI.Service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

@RequestMapping("/template")
public class Template {
    
@Qualifier("ollamaChatClient")
private ChatClient ollamaChatClient;
    public String promptTemplate() {

        // PromptTemplate promptTemplate2 = PromptTemplate.builder()
        //     .template("Explain about {topic} and give one {this} example?")
        //     .build();
        // String reString = promptTemplate2.render(Map.of("topic", "java", "this", "code"));
        // System.out.println(reString);

        var systemPromptTemplate = SystemPromptTemplate.builder()
                .template("Explain about {topic} and give one {type} example?")
                .build()
                .createMessage(Map.of("topic", "Java","type","Small DSA"));

        var userTemplate = PromptTemplate.builder()
                .template("Explain about {topic} and give two {type} example")
                .build()
                .createMessage(Map.of("topic", "Python","type","small"));

        Prompt prompt = new Prompt(systemPromptTemplate,userTemplate);

        System.out.println(prompt);
        return ollamaChatClient.prompt(prompt).call().content();
    }
}
