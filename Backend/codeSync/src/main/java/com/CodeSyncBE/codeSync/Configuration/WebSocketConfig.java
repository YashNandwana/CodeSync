package com.CodeSyncBE.codeSync.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-endpoint", "/chat-endpoint")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
//        registry.addEndpoint("/chat-endpoint") // Add endpoint for chat messages
//                .setAllowedOrigins("http://localhost:3000/")
//                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/editor", "/chatroom");
        config.setApplicationDestinationPrefixes("/app");
    }


}
