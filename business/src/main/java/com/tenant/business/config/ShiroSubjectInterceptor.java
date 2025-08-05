package com.tenant.business.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class ShiroSubjectInterceptor implements ChannelInterceptor {

    @Autowired
    private org.apache.shiro.mgt.SecurityManager securityManager;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = (String) accessor.getSessionAttributes().get("sessionId");

        if (sessionId != null) {
            try {
                // 1. 从 Redis 中获取 Shiro Session
                Session shiroSession = securityManager.getSession(new DefaultSessionKey(sessionId));

                // 2. 构建 Subject 并绑定到当前线程
                Subject subject = new Subject.Builder(securityManager)
                        .sessionId(sessionId)
                        .buildSubject();
                ThreadContext.bind(subject);

                // 3. 可选：存储 Subject 到消息头
                accessor.getSessionAttributes().put("shiroSubject", subject);
            } catch (SessionException e) {
                throw new RuntimeException("Session 已过期或无效", e);
            }
        }
        return message;
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        ThreadContext.unbindSubject(); // 清理线程绑定
    }
}