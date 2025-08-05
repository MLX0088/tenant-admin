package com.tenant.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenant.business.Global;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.business.utils.HttpUtil;
import com.tenant.common.annotation.Log;
import com.tenant.common.constant.Constants;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysDept;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.core.redis.RedisCache;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.exception.user.UserNotExistsException;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.framework.manager.AsyncManager;
import com.tenant.framework.manager.factory.AsyncFactory;
import com.tenant.framework.shiro.service.SysPasswordService;
import com.tenant.system.service.ISysDeptService;
import com.tenant.system.utils.JwtUtils;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ServletUtils;
import com.tenant.common.utils.StringUtils;
import com.tenant.framework.web.service.ConfigService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证
 * 
 * @author tenant
 */
@Controller
public class SysLoginController extends BaseController
{
    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;

    @Value("${sms.url}")
    private String smsUrl;
    @Value("${sms.expireMin}")
    private int smsExpireMin;
    @Autowired
    private ConfigService configService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    protected RedisCache redisCache;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        // 是否开启记住我
        mmap.put("isRemembered", rememberMe);
        // 是否开启用户注册
        mmap.put("isAllowRegister", Convert.toBool(configService.getKey("sys.account.registerUser"), false));
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            SysUser sysUser = ShiroUtils.getSysUser();

            if(Global.isUrgentModel){
                if("00".equals(sysUser.getUserType()) && !sysUser.isAdmin()){
                    return error("登录失败");
                }
            }

            if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户不允许登录
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "不支持登录该系统"));
                throw new UserNotExistsException();
            }
            SysDept dept = deptService.selectDeptById(sysUser.getDeptId());
            if("1".equals(dept.getStatus())){
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "该系统权限已停用，请联系管理员"));
                throw new UserNotExistsException();
            }

            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }



    @PostMapping("/loginApp")
    @ResponseBody
    public AjaxResult ajaxLoginApp(@RequestBody Map<String, Object> jsonMap,HttpServletResponse response)
    {
        String loginName = (String)jsonMap.get("loginName");
        String password = (String)jsonMap.get("password");
//        String formId = (String)jsonMap.get("formId");
//        String validateCode = (String)jsonMap.get("validateCode");
//        Long tenantId = null;
//        if(jsonMap.get("tenantId") != null){
//            tenantId = (Long)jsonMap.get("tenantId");
//        }
//        boolean checkCode = true;
//        if(tenantId != null){
//            TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(tenantId);
//            if(config!=null){
//                if("1".equals(config.getNeedLoginCode())){
//                    checkCode = false;
//                }
//            }
//        }
//        if (checkCode){
//            if(validateCode == null){
//                return error("验证码不能为空");
//            }
//            String code = redisCache.getCacheObject(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY+"_"+formId);
//            if(code == null){
//                return error("验证码已过时，有效期两分钟");
//            }
//            if(!code.equals(validateCode)){
//                return error("验证码错误");
//            }
//        }
        Boolean rememberMe = Boolean.parseBoolean(jsonMap.get("rememberMe").toString());
        long deptId = Long.parseLong(jsonMap.get("deptId").toString());
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            SysUser sysUser = ShiroUtils.getSysUser();
            if (!UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户才允许登录
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, "不支持登录APP"));
                throw new UserNotExistsException();
            }
            if(deptId != sysUser.getDeptId().longValue()){
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, "不同租户用户不能登录"));
                throw new UserNotExistsException();
            }
            SysDept dept = deptService.selectDeptById(sysUser.getDeptId());
            if("1".equals(dept.getStatus())){
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, "该系统已停用，请联系管理员"));
                throw new UserNotExistsException();
            }

            Map<String,Object> map = new HashMap<>();
            map.put("deptId",sysUser.getDeptId());
            map.put("score",sysUser.getScore());
            map.put("headImageId",sysUser.getHeadImageId());
            map.put("userId",sysUser.getUserId());
            map.put("qqNumber",sysUser.getQqNumber());
            map.put("grade",sysUser.getGrade());
            map.put("loginName",sysUser.getLoginName());
            map.put("userName",sysUser.getUserName());
            map.put("totalAmount",sysUser.getTotalAmount());
            map.put("createTime",sysUser.getCreateTime());
            map.put("channalId",sysUser.getChannalId());

            ObjectMapper mapper = new ObjectMapper();
            // 生成 JWT 并写入 Header
            String jwt = JwtUtils.generateToken(mapper.writeValueAsString(sysUser));
            response.setHeader("Authorization", "Bearer " + jwt);
            return AjaxResult.success(map);
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/captchaApp")
    @ResponseBody
    public AjaxResult captchaApp(@RequestBody Map<String, Object> jsonMap)
    {
        String loginName = (String)jsonMap.get("loginName");
        long hash = loginName.hashCode() ^ System.currentTimeMillis();
        int code = (int) (Math.abs(hash) % 900000 + 100000);
        String captcha = String.format("%06d", code); // 补足6位前导零
        redisCache.setCacheObject("captcha_"+loginName,captcha,smsExpireMin*60, TimeUnit.SECONDS);
        System.out.println(captcha);
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("code", code);
        jsonData.put("number",smsExpireMin );
        jsonData.put("targets", loginName);
        JSONObject result = HttpUtil.postJson(smsUrl, jsonData);
        if(result.getInteger("code")==200){
            return success("验证码已发送，"+smsExpireMin+"分钟内有效");
        }else{
            logger.error("短信发送失败："+result.toJSONString());
        }
        return success("验证码发送失败，请稍后再试！");
    }

    @PostMapping("/checkCaptchaApp")
    @ResponseBody
    public AjaxResult checkCaptchaApp(@RequestBody Map<String, Object> jsonMap)
    {
        String loginName = (String)jsonMap.get("loginName");
        String captcha = (String)jsonMap.get("captcha");
        String cacheCaptcha = redisCache.getCacheObject("captcha_"+loginName);
        if(captcha.equals(cacheCaptcha)){
            return success();
        }
        return error("验证码无效");
    }

    @Log(title = "忘记密码重置", businessType = BusinessType.UPDATE)
    @PostMapping("/resetForgotPwd")
    @ResponseBody
    public AjaxResult resetForgotPwd(@RequestBody  SysUser user)
    {
        SysUser temp = userService.selectUserByLoginName(user.getLoginName());
        user.setUserId(temp.getUserId());
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword1(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            return success();
        }
        return error();
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }


    @GetMapping("/v1/userinfo")
    @ResponseBody
    public AjaxResult userinfo()
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        sysUser = userService.selectUserById(sysUser.getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("deptId",sysUser.getDeptId());
        map.put("score",sysUser.getScore());
        map.put("headImageId",sysUser.getHeadImageId());
        map.put("userId",sysUser.getUserId());
        map.put("qqNumber",sysUser.getQqNumber());
        map.put("grade",sysUser.getGrade());
        map.put("loginName",sysUser.getLoginName());
        map.put("userName",sysUser.getUserName());
        map.put("totalAmount",sysUser.getTotalAmount());
        map.put("createTime",sysUser.getCreateTime());
        map.put("channalId",sysUser.getChannalId());
        return AjaxResult.success(map);
    }
    @PostMapping("/v1/updateQQ")
    @ResponseBody
    public AjaxResult updateQQ(@RequestBody SysUser sysUser)
    {
        sysUser.setUserId(ShiroUtils.getUserId());
        userService.updateUser(sysUser);
        return AjaxResult.success();
    }
    @PostMapping("/v1/updateUserName")
    @ResponseBody
    public AjaxResult updateUserName(@RequestBody SysUser sysUser)
    {
        sysUser.setUserId(ShiroUtils.getUserId());
        userService.updateUserInfo(sysUser);
        return AjaxResult.success();
    }
    @PostMapping("/v1/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestBody SysUser sysUser)
    {
        sysUser.setUserId(ShiroUtils.getUserId());
        userService.updateHeadImage(sysUser);
        return AjaxResult.success();
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/v1/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(@RequestBody  SysUser user)
    {
        user.setUserId(ShiroUtils.getUserId());
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword1(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
            {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @Log(title = "退出登录", businessType = BusinessType.UPDATE)
    @GetMapping("/v1/logoutApp")
    @ResponseBody
    public AjaxResult logoutApp()
    {
        ShiroUtils.logout();
        return success();
    }
}
