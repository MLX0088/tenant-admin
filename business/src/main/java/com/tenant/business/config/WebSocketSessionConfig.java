package com.tenant.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
public class WebSocketSessionConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Bean
    public DefaultHandshakeHandler handshakeHandler() {
        // 允许从 HTTP 请求中获取 Session
        return new DefaultHandshakeHandler(new TomcatRequestUpgradeStrategy());
    }
}
