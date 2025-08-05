package com.tenant.framework.interceptor;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.redis.RedisCache;
import com.tenant.common.json.JSON;
import com.tenant.common.utils.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImgbedInterceptor implements HandlerInterceptor {
    public final UrlPathHelper urlPathHelper = new UrlPathHelper();
    public final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final List<String> excludesList = new ArrayList<String>();
    private static Logger logger = LoggerFactory.getLogger(ImgbedInterceptor.class);
    @Autowired
    protected RedisCache redisCache;
    @PostConstruct
    public void post(){
       excludesList.add("/imgbed/user/register");
       excludesList.add("/imgbed/user/getCode");
       excludesList.add("/imgbed/user/login");
       excludesList.add("/imgbed/user/resetpwd");
       excludesList.add("/imgbed/file/upload");
        excludesList.add("/imgbed/file/uploadUrl");
       excludesList.add("/imgbed/pay/aliPayCertNotify");
       excludesList.add("/imgbed/pay/aliPayCertReturn");
       excludesList.add("/imgbed/pay/wechatPayNotify");
       excludesList.add("/imgbed/pay/doAliPay");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      /*  String token = request.getHeader("token");
        if (token != null) {

            redisCache.expire("token:"+token,Integer.valueOf(redisCache.getCacheObject("sys_config:session.timeout")) , TimeUnit.MINUTES);
        }*/
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = urlPathHelper.getPathWithinApplication(request);
        //不会创建seeeion 判断用户有没有登录
        Subject subject = SecurityUtils.getSubject();
        for (String excludeUrl : excludesList) {
            //通过后台管理操作的接口也要去除
            if (antPathMatcher.match(excludeUrl, url)||subject.isAuthenticated()) {
                logger.info("去除的请求:" + url);
                return  true;
            }
        }
        String token = request.getHeader("token");
        Object cacheObject = redisCache.getCacheObject("imgbed:token:" + token);
        if (token == null|| cacheObject ==null) {
            AjaxResult ajaxResult = AjaxResult.timeOut();
            ServletUtils.renderString(response, JSON.marshal(ajaxResult));
            return false;
        }
        request.setAttribute("imgbedUser",cacheObject);
        return  true;
    }
}
