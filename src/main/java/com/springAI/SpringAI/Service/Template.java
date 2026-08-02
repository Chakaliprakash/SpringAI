package com.springAI.SpringAI.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

@RequestMapping("/template")
public class Template {
    
@Qualifier("ollamaChatClient")
private final ChatClient ollamaChatClient;

@Value("classpath:/prompts/user-prompts")
private Resource promptResource;

@Value("classpath:/prompts/system-prompts")
private Resource systemResource;


    public String promptTemplate() {

        // PromptTemplate promptTemplate2 = PromptTemplate.builder()
        //     .template("Explain about {topic} and give one {this} example?")
        //     .build();
        // String reString = promptTemplate2.render(Map.of("topic", "java", "this", "code"));
        // System.out.println(reString);

        // var you=StTemplateRenderer.builder().build();
        var systemPromptTemplate = SystemPromptTemplate.builder()
                .template("""
                        You are a helpful AI assistant.
                        Answer the user's question about <topic>.
                        """)
                .build()
                .createMessage(Map.of("topic", "Java"));

        var userTemplate = PromptTemplate.builder()
                .template("Explain about {topic} and give two {type} example")
                .build()
                .createMessage(Map.of("topic", "Python","type","small"));

        Prompt prompt = new Prompt(systemPromptTemplate,userTemplate);

        System.out.println(prompt);
        return ollamaChatClient.prompt(prompt).call().content();
    }
    public String resourcePrompts() {
        System.out.println("===== System Prompt =====");
        try {
            System.out.println(systemResource.getContentAsString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }

        System.out.println("===== User Prompt =====");
        try {
            System.out.println(promptResource.getContentAsString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
        
        var response= ollamaChatClient.prompt()
                .system(system->system.text(systemResource))
                .user(user->user.text(promptResource).param("topic", "java"))
                .call() 
                .content();
                System.out.println("Response for resource files : "+response);
                return response;
    }
}
