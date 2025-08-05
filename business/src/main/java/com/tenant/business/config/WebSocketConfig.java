package com.tenant.business.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private ShiroSubjectInterceptor shiroSubjectInterceptor;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat").addInterceptors(new SessionHandshakeInterceptor()).setAllowedOriginPatterns("*");
    }
    private static long HEART_BEAT = 10000;
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
//        te.setPoolSize(1);
//        te.setThreadNamePrefix("wss-heartbeat-thread");
//        te.initialize();

        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/business/ChatMessage");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(shiroSubjectInterceptor);
    }

}
