package com.tenant.business.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

public class SessionHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            attributes.put("ip", servletRequest.getServletRequest().getRemoteAddr()); // 存储到 WebSocket 会话属性
            // 从请求头获取 Token
            String token = request.getHeaders().getFirst("Authorization");
            if(servletRequest.getServletRequest().getCookies() !=null){
                // 从 Cookie 中获取 Session ID
                String sessionId = Arrays.stream(servletRequest.getServletRequest().getCookies())
                        .filter(cookie -> "SHIROSESSIONID".equals(cookie.getName()))
                        .findFirst()
                        .map( javax.servlet.http.Cookie::getValue)
                        .orElse(null);
                if (sessionId != null) {
                    attributes.put("sessionId", sessionId); // 存储到 WebSocket 会话属性
                }
            }
            else if(!StringUtils.isEmpty(token)){
                if (token != null && token.startsWith("Bearer ")) {
                    String jwt = token.substring(7);
                    // 验证 Token（例如使用 JWT 工具类）

                    Subject subject = new Subject.Builder().authenticated(true).buildSubject();
                    subject.login(new BearerToken(jwt)); // 使用自定义 Token 登录
                    ThreadContext.bind(subject);
                    String userStr = JwtUtils.parseToken(jwt); // 解析 JWT
//                    if (!StringUtils.isEmpty(userStr)) {
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        try {
//                            SysUser user = objectMapper.readValue(userStr, SysUser.class);
//                        } catch (JsonProcessingException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                    return true;
                }
            }

        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {}
}
