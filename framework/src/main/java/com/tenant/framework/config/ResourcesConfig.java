package com.tenant.framework.config;

import com.tenant.framework.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.tenant.common.config.TenantConfig;
import com.tenant.common.constant.Constants;

/**
 * 通用配置
 * 
 * @author tenant
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    /**
     * 首页地址
     */
    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    @Autowired
    KnowledgeInterceptor knowledgeInterceptor;

    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("forward:" + indexUrl);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + TenantConfig.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //1.加入的顺序就是拦截器执行的顺序，设置@Order也不会影响
        //2.按顺序执行所有拦截器的preHandle
        //3.所有的preHandle 执行完再反向执行全部postHandle 最后是反向执行afterCompletion
        registry.addInterceptor(knowledgeInterceptor).addPathPatterns("/kl/**");   //知识付费
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
}