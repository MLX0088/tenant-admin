package com.tenant.framework.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenant.system.utils.JwtUtils;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.exception.user.CaptchaException;
import com.tenant.common.exception.user.RoleBlockedException;
import com.tenant.common.exception.user.UserBlockedException;
import com.tenant.common.exception.user.UserNotExistsException;
import com.tenant.common.exception.user.UserPasswordNotMatchException;
import com.tenant.common.exception.user.UserPasswordRetryLimitExceedException;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.framework.shiro.service.SysLoginService;
import com.tenant.system.service.ISysMenuService;
import com.tenant.system.service.ISysRoleService;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author tenant
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken || token instanceof BearerToken;
    }
    /**
     * 授权 优先从 authorizationCache取
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        SysUser user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证,优先从 authenticationCache取值
     protected Object getAuthenticationCacheKey(AuthenticationToken token) {
     return token != null ? token.getPrincipal() : null; authenticationCache
     }
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        SimpleAuthenticationInfo info = null;
        SysUser user = null;
        if(token instanceof UsernamePasswordToken){

            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            String username = upToken.getUsername();
            String password = "";
            if (upToken.getPassword() != null)
            {
                password = new String(upToken.getPassword());
            }
            try
            {
                user = loginService.login(username, password);
            }
            catch (CaptchaException e)
            {
                throw new AuthenticationException(e.getMessage(), e);
            }
            catch (UserNotExistsException e)
            {
                throw new UnknownAccountException(e.getMessage(), e);
            }
            catch (UserPasswordNotMatchException e)
            {
                throw new IncorrectCredentialsException(e.getMessage(), e);
            }
            catch (UserPasswordRetryLimitExceedException e)
            {
                throw new ExcessiveAttemptsException(e.getMessage(), e);
            }
            catch (UserBlockedException e)
            {
                throw new LockedAccountException(e.getMessage(), e);
            }
            catch (RoleBlockedException e)
            {
                throw new LockedAccountException(e.getMessage(), e);
            }
            catch (Exception e)
            {
                log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
                throw new AuthenticationException(e.getMessage(), e);
            }
            info = new SimpleAuthenticationInfo(user, password, getName());
        }
        else if (token instanceof BearerToken) {
            // 处理 Bearer Token
            String jwt = ((BearerToken) token).getToken();
            String userStr = JwtUtils.parseToken(jwt); // 解析 JWT
            if (userStr == null) {
                throw new AuthenticationException("无效 Token");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                user = objectMapper.readValue(userStr, SysUser.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            info = new SimpleAuthenticationInfo(user, jwt, getName());
        }
        return info;
    }

    /**
     * 清理指定用户授权信息缓存
     */
    public void clearCachedAuthorizationInfo(Object principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理所有用户授权信息缓存
     */
    public void clearAllCachedAuthorizationInfo()
    {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null)
        {
            /*for (Object key : cache.keys())
            {
                cache.remove(key);  //会自动带上前缀，导致删除失败错误
            }*/
            cache.clear();
        }
    }
}
