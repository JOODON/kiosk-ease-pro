package com.example.kioskeasepro.config.socketConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/menu");
        config.setApplicationDestinationPrefixes("/app");
    }
    // /app 으로 시작하는 메시지가 @MessageMapping 으로 매핑되고,
    // /menu 으로 시작하는 메시지가 클라이언트로 전송되도록 구성
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-endpoint").withSockJS();
    }
}
