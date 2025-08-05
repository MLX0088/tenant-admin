package com.tenant.web.controller.system;

import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.domain.form.RegisterForm;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.common.utils.AddressUtils;
import com.tenant.common.utils.ServletUtils;
import com.tenant.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.StringUtils;
import com.tenant.framework.shiro.service.SysRegisterService;
import com.tenant.system.service.ISysConfigService;

import java.math.BigDecimal;

/**
 * 注册验证
 * 
 * @author tenant
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ITbTenantConfigService tbTenantConfigService;

    @Autowired
    private ITbAvatarService tbAvatarService;

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @PostMapping("/registerApp")
    @ResponseBody
    public AjaxResult registerApp(@RequestBody RegisterForm user)
    {
        if(user.getDeptId() != null){
            TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(user.getDeptId());
            if(config == null){
                return error("当前系统未开放使用，请联系管理员！");
            }
            if(config.getCanInvite().equals("0")){
                if(!user.getInviteCode().equals(config.getInviteCode())){
                    return error("邀请码无效！");
                }
            }
        }else{
            if(user.getInviteCode() != null){
                TbTenantConfig config = tbTenantConfigService.getByInviteCode(user.getInviteCode());
                if(config == null){
                    return error("邀请码无效！");
                }
                user.setDeptId(config.getTenantId());
            }

        }

        String cacheCaptcha = redisCache.getCacheObject("captcha_"+user.getLoginName());
        if(cacheCaptcha==null || !cacheCaptcha.equals(user.getCaptcha())){
            return error("验证码已过期或无效！");
        }

        SysUser result = new SysUser();
        result.setDeptId(user.getDeptId());
        result.setScore(new BigDecimal(0));
        result.setHeadImageId(tbAvatarService.getRandomIdForTenant(user.getDeptId()));
        result.setGrade(1l);
        result.setUserName(generateRandomName());
        result.setTotalAmount(0l);
        result.setUserType("1");
        result.setPassword(user.getPassword());
        result.setLoginName(user.getLoginName());
        result.setPhonenumber(result.getLoginName());
        result.setRegistChannalId(user.getChannelId());
        result.setRegisterIp(ServletUtils.getRequest().getRemoteAddr());

        if(sysUserService.selectByIp(result) != null){
            result.setRegisterIpRepeat(1);
        }
        result.setRegisterAddress(AddressUtils.getRealAddressByIP(ServletUtils.getRequest().getRemoteAddr()));
        String msg = registerService.registerApp(result);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    private String generateRandomName(){
        String[] firstName = new String[]{"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴", "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗", "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧", "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕", "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "薛", "叶", "阎", "余", "潘", "杜", "戴", "夏", "钟", "汪", "田", "任", "姜", "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆", "郝", "孔", "白", "崔", "康", "毛", "邱", "秦", "江", "史", "顾", "侯", "邵", "孟", "龙", "万", "段", "漕", "钱", "汤", "尹", "黎", "易", "常", "武", "乔", "贺", "赖", "龚", "文"};
        String[]  lastName = new String[]{"萱","涵","梓","宇","浩","欣","怡","子","雨","诗", "思","梦","嘉","文","雅","晨","若","婉","灵","韵", "瑜","彤","玥","琳","瑞","辰","彦","轩","逸","然", "哲","俊","杰","悦","宁","琪","瑶","雪","岚","羽", "熙","冉","奕","航","昊","皓","承","诺","清","沐", "云","芊","冉","桐","乐","阳","智","宜","安","希", "之","景","明","朗","润","达","宏","博","卓","远", "彬","泽","烨","霖","臻","骏","骁","翊","恺","硕", "恒","川","柯","南","瑾","璇","露","冰","夏","秋", "伊","朵","溪","蔓","橙","檬","棠","樱","檀","柳", "卉","竹","兰","菊","梅","荷","芷","薇","茜","茉", "芙","蓉","蓓","蕾","霓","裳","霄","霜","霁","霖", "晓","晗","昕","昭","曜","晖","曦","晟","昱","昶", "嵘","峻","岳","嵩","岩","川","河","江","海","洋", "洲","沛","淳","渊","澈","泓","淼","澜","汀","汐", "渊","潇","湛","沁","汶","津","汶","汶","沅","沂", "洛","淇","涓","漪","灏","瀚","澎","湃","潇","湘", "牧","隗","谷","宓","蓬","全","郗","班","仰","仲", "伊","宫","宁","栾","甘","厉","戎","祖","武","符", "景","詹","束","韶","郜","黎","溥","印","宿","怀"};
        int fnLength = firstName.length;
        int lnLength = lastName.length;
        int fnIndex = (int) Math.floor(Math.random() * fnLength);
        String name = "";
        for (int i = 0; i < 2; i++) {
            int lnIndex = (int) Math.floor(Math.random() * fnLength);
            name += lastName[lnIndex];
        }
        return firstName[fnIndex]+name;
    }
}
