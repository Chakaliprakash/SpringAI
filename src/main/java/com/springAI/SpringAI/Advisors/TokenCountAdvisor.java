package com.springAI.SpringAI.Advisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class TokenCountAdvisor implements CallAdvisor {

    private final Logger logger = LoggerFactory.getLogger(TokenCountAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        this.logger.info("My Token Counter Advisor is called : ");

        // this.logger.info("To String Test : "+ chatClientRequest.toString());
        this.logger.info("Request prompt : "+ chatClientRequest.prompt().getContents());
        // this.logger.info("Copy : "+ chatClientRequest.copy());
        // this.logger.info("Copy + String : "+ chatClientRequest.copy().toString());


        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        this.logger.info("Response Received From Token Advisor");
        this.logger.info("Total tokens: " + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    

}
