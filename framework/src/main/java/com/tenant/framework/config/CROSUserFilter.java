package com.tenant.framework.config;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class CROSUserFilter extends UserFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

            setHeader(httpRequest, httpResponse);
            if ("OPTIONS".equals(httpRequest.getMethod())) {
                return false;
            }
            return true;

    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, accept, Authorization");
        response.setHeader("Access-Control-expose-headers", "Authorization");
        response.setStatus(HttpStatus.OK.value());
    }
}
