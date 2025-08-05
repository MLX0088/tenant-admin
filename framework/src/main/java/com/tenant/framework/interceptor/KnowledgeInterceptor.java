package com.tenant.framework.interceptor;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.json.JSON;
import com.tenant.common.utils.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class KnowledgeInterceptor implements HandlerInterceptor {
    public final UrlPathHelper urlPathHelper = new UrlPathHelper();
    public final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final List<String> excludesList = new ArrayList<String>();
    private static Logger logger = LoggerFactory.getLogger(KnowledgeInterceptor.class);
    @PostConstruct
    public void post(){
        /*kl开头的才会过来*/
       excludesList.add("/kl/register");
       excludesList.add("/kl/login");
       excludesList.add("/kl/resetpwd");
       excludesList.add("/kl/order/aliPayCertNotify");
       excludesList.add("/kl/order/aliPayCertReturn");
       excludesList.add("/kl/order/wechatPayNotify");
       excludesList.add("/kl/order/placeAnOrder");
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
        Subject subject = SecurityUtils.getSubject();
        for (String excludeUrl : excludesList) {
            if (antPathMatcher.match(excludeUrl, url)||subject.isAuthenticated()) {
                logger.info("去除的请求:" + url);
                return  true;
            }
        }
        if (!subject.isAuthenticated()) {
            AjaxResult ajaxResult = AjaxResult.timeOut();
//            response.setStatus(401); 由code 判断是否超时，统一状态码200
            ServletUtils.renderString(response, JSON.marshal(ajaxResult));
            return false;
        }
        return  false;
    }
}
