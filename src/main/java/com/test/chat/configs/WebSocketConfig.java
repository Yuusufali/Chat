package com.test.chat.configs;

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
        config.enableSimpleBroker("/topic");
        // Этот метод включает простой брокер сообщений, который передает сообщения всем подписчикам, зарегистрированным на определенные темы (в данном случае - "/topic")
        config.setApplicationDestinationPrefixes("/app");
        //  Этот метод устанавливает префикс, который будет добавлен к адресам, к которым обращаются клиенты при отправке сообщений
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws2")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        // Этот метод добавляет конечную точку "/ws" для подключения к WebSocket серверу и включает поддержку SockJS,
        // что позволяет использовать WebSocket в браузерах, которые не поддерживают его напрямую.
    }
}