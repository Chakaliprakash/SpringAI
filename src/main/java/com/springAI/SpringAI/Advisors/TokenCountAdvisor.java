package com.springAI.SpringAI.Advisors;


import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class TokenCountAdvisor implements CallAdvisor,StreamAdvisor {

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

    @Override
public Flux<ChatClientResponse> adviseStream(
        ChatClientRequest chatClientRequest,
        StreamAdvisorChain streamAdvisorChain) {

    Flux<ChatClientResponse> chatFlux = streamAdvisorChain.nextStream(chatClientRequest);

    logger.info("Request in Stream: {}", chatClientRequest.prompt().getContents());

    AtomicInteger totalTokens = new AtomicInteger();
    StringBuilder responseText = new StringBuilder();

    return chatFlux
            .doOnNext(response -> {

                if (response.chatResponse() != null
                        && response.chatResponse().getMetadata() != null
                        && response.chatResponse().getMetadata().getUsage() != null) {

                    totalTokens.set(
                            response.chatResponse()
                                    .getMetadata()
                                    .getUsage()
                                    .getTotalTokens());
                }

                responseText.append(
                        response.chatResponse()
                                .getResult()
                                .getOutput()
                                .getText());
            })
            .doOnCancel(()->{logger.info("Response from Stream: {}", responseText);
                logger.info("Total tokens in Stream: {}", totalTokens.get());})

            .doOnComplete(() -> {
                logger.info("Response from Stream: {}", responseText);
                logger.info("Total tokens in Stream: {}", totalTokens.get());
            })
            
            .doFinally(a->{
                logger.info("Response from Stream: {}", responseText);
                logger.info("Total tokens in Stream: {}", totalTokens.get());
            });
}
}


    


