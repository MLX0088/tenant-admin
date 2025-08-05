package com.tenant.framework.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BearerTokenFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return new BearerToken(authHeader.substring(7)); // 提取 Token
        }
        return null; // 没有 Token 则跳过
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 调用 executeLogin 进行认证
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            sendUnauthorized(response, "登录已失效或者未登录");
            return false;
        }
        return executeLogin(request, response);
    }
    private void sendUnauthorized(ServletResponse response, String message) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json");
        httpResponse.getWriter().write("{\"msg\": \"" + message + "\",\"code\": 401}");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 如果已通过其他方式（如 Cookie）认证，直接放行
        return getSubject(request, response).isAuthenticated();
    }

    @Override
    protected boolean onLoginFailure(
            AuthenticationToken token,
            AuthenticationException e,
            ServletRequest request,
            ServletResponse response
    ) {
        // 处理认证失败（例如返回 401）
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
