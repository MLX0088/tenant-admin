package com.tenant.framework.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.tenant.common.json.JSON;
import com.tenant.common.annotation.RepeatSubmit;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.ServletUtils;

/**
 * 防止重复提交拦截器
 *
 * @author tenant
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${env}")
    private String env;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if("dev".equals(env)){
            logger.info("uri------------>  {}",request.getRequestURI());
        }
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request, annotation))
                {
                    AjaxResult ajaxResult = AjaxResult.error(annotation.message());
                    ServletUtils.renderString(response, JSON.marshal(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request 请求对象
     * @param annotation 防复注解
     * @return 结果
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws Exception;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
