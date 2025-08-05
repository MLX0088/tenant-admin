package com.tenant.web.controller.system;

import java.io.Serializable;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.tenant.business.Global;
import com.tenant.common.core.domain.entity.SysDept;
import com.tenant.common.utils.*;
import com.tenant.common.utils.spring.SpringUtils;
import com.tenant.system.domain.SysUserOnline;
import com.tenant.system.service.ISysDeptService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.config.TenantConfig;
import com.tenant.common.constant.ShiroConstants;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.entity.SysMenu;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.core.text.Convert;
import com.tenant.framework.shiro.service.SysPasswordService;
import com.tenant.system.service.ISysConfigService;
import com.tenant.system.service.ISysMenuService;
import org.apache.shiro.mgt.SecurityManager;

/**
 * 首页 业务处理
 * 
 * @author tenant
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    // 锁定屏幕
    @PostMapping("/changeSelectDeptId")
    @ResponseBody
    public AjaxResult changeSelectDeptId(@RequestParam Long id)
    {
        try {
            SysUser user = ShiroUtils.getSysUser();
            user.setSelectDeptId(id);
            ShiroUtils.setSysUser(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success(ShiroUtils.getSysUser().getSelectDeptId());
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        Boolean footer = Convert.toBool(configService.selectConfigByKey("sys.index.footer"), true);
        Boolean tagsView = Convert.toBool(configService.selectConfigByKey("sys.index.tagsView"), true);
        mmap.put("footer", footer);
        mmap.put("tagsView", tagsView);
        mmap.put("mainClass", contentMainClass(footer, tagsView));
        mmap.put("copyrightYear", TenantConfig.getCopyrightYear());
        mmap.put("demoEnabled", TenantConfig.isDemoEnabled()&&user.isAdmin());
        mmap.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        mmap.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));
        mmap.put("isMobile", ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")));
        mmap.put("deptList",sysDeptService.selectDeptList(new SysDept()));
        mmap.put("selectDeptId",ShiroUtils.getSysUser().getSelectDeptId() == null ? 0l : ShiroUtils.getSysUser().getSelectDeptId());
        mmap.put("isBanBettingModel", Global.isBanBettingModel);
        mmap.put("isUrgentModel", Global.isUrgentModel);

        // 菜单导航显示风格
        String menuStyle = configService.selectConfigByKey("sys.index.menuStyle");
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies)
        {
            if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName()))
            {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        return webIndex;
    }

    @PostMapping("/changeUrgentModel")
    @ResponseBody
    public AjaxResult changeUrgentModel(@RequestParam boolean model)
    {
        Global.isUrgentModel = model;
        if(Global.isUrgentModel){
            Collection<Session> sessions = redisSessionDAO.getActiveSessions();
            Iterator<Session> it = sessions.iterator();
            SecurityManager securityManager = SecurityUtils.getSecurityManager();
            while (it.hasNext()) {
                Session session = it.next();
                SysUser userOnline = getSession(session);
                if(userOnline!=null){
                    String sessionId = session.getId().toString();
                    String loginName = userOnline.getLoginName();
                    if("00".equals(userOnline.getUserType())){
                        redisSessionDAO.delete(redisSessionDAO.readSession(sessionId));
                        removeUserCache(loginName, sessionId);

                        Subject currentUser = new Subject.Builder(securityManager).session(session).buildSubject();
                        currentUser.logout();

                    }

                }
            }

        }
        return AjaxResult.success(Global.isUrgentModel);
    }

    private SysUser getSession(Session session)
    {
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj)
        {
            return null;
        }
        if (obj instanceof SimplePrincipalCollection)
        {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof SysUser)
            {
                SysUser sysUser = (SysUser) obj;
                return sysUser;
            }
        }
        return null;
    }

    public void removeUserCache(String loginName, String sessionId)
    {
        Cache<String, Deque<Serializable>> cache = SpringUtils.getBean(RedisCacheManager.class).getCache(ShiroConstants.SYS_USERCACHE);
        Deque<Serializable> deque = cache.get(loginName);
        if (StringUtils.isEmpty(deque) || deque.size() == 0)
        {
            return;
        }
        deque.remove(sessionId);
        cache.put(loginName, deque);
    }

    @PostMapping("/changeBanBettingModel")
    @ResponseBody
    public AjaxResult changeBanBettingModel(@RequestParam boolean model)
    {
        Global.isBanBettingModel = model;
        return AjaxResult.success(Global.isBanBettingModel);
    }


    // 锁定屏幕
    @GetMapping("/lockscreen")
    public String lockscreen(ModelMap mmap)
    {
        mmap.put("user", getSysUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 解锁屏幕
    @PostMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password)
    {
        SysUser user = getSysUser();
        if (StringUtils.isNull(user))
        {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        if (passwordService.matches(user, password))
        {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin()
    {
        return "skin";
    }

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response)
    {
        CookieUtils.setCookie(response, "nav-style", style);
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", TenantConfig.getVersion());
        return "main";
    }

    // content-main class
    public String contentMainClass(Boolean footer, Boolean tagsView)
    {
        if (!footer && !tagsView)
        {
            return "tagsview-footer-hide";
        }
        else if (!footer)
        {
            return "footer-hide";
        }
        else if (!tagsView)
        {
            return "tagsview-hide";
        }
        return StringUtils.EMPTY;
    }

    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate)
    {
        Integer initPasswordModify = Convert.toInt(configService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify != null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate)
    {
        Integer passwordValidateDays = Convert.toInt(configService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays != null && passwordValidateDays > 0)
        {
            if (StringUtils.isNull(pwdUpdateDate))
            {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            Date nowDate = DateUtils.getNowDate();
            return DateUtils.differentDaysByMillisecond(nowDate, pwdUpdateDate) > passwordValidateDays;
        }
        return false;
    }
}
