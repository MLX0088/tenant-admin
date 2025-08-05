package com.tenant.framework.interceptor;

import com.tenant.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class NxInterceptor implements HandlerInterceptor {
    @Autowired
    protected RedisCache redisCache;
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String token = request.getHeader("token");
        if (token != null) {

            redisCache.expire("token:"+token,Integer.valueOf(redisCache.getCacheObject("sys_config:session.timeout")) , TimeUnit.MINUTES);
        }
    }

/*    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        *//**设置响应头允许ajax跨域访问**//*
        response.setHeader("Access-Control-Allow-Origin","*");
        *//*星号表示所有的异域请求都可以接受，*//*
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        response.setHeader("Access-Control-Allow-Headers","*");
        String token = request.getHeader("token");
        if (token != null||redisCache.getCacheObject("token:"+token)==null) {
            AjaxResult ajaxResult = AjaxResult.timeOut();
            ServletUtils.renderString(response, JSON.marshal(ajaxResult));
            return false;
        }
        return  true;
    }*/
}
