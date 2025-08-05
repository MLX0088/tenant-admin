package com.tenant.framework.shiro.session;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class TokenSessionManager extends DefaultWebSessionManager {
    /**
     * 自定义sessionID,从请求头token获取
     * @param request
     * @param response
     * @return
     */
   public Serializable getSessionId(ServletRequest request, ServletResponse response) {
       Serializable sessionId = super.getSessionId(request, response);
       if (sessionId == null) {
           HttpServletRequest httpServletRequest = (HttpServletRequest) request;
           sessionId=  httpServletRequest.getHeader("token");
       }
       return sessionId;
    }
}
