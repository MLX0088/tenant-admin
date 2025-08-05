package com.tenant.common.core.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tenant.common.annotation.Excel;
import com.tenant.common.annotation.Excel.ColumnType;
import com.tenant.common.annotation.Excel.Type;
import com.tenant.common.annotation.Excels;
import com.tenant.common.core.domain.BaseEntity;
import com.tenant.common.xss.Xss;

/**
 * 用户对象 sys_user
 *
 * @author tenant
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /** 部门父ID */
    private Long parentId;

    /** 角色ID */
    private Long roleId;

    /** 登录名称 */
    @NotEmpty(message = "登录名称不能为空",groups = ResetPwd.class)
    @Excel(name = "登录名称")
    private String loginName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户类型 */
    private String userType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @NotEmpty(message = "手机号码不能为空")
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;
    @NotEmpty(message = "密码不能为空",groups = ResetPwd.class)
    /** 密码 */
    @JsonIgnore
    private String password;
    @JsonIgnore
    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    private String registerIp;
    private String registerAddress;
    private int registerIpRepeat;
    private Date lastScoreTime;

    /** 密码最后更新时间 */
    private Date pwdUpdateDate;

    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 头像 */
    @Excel(name = "头像")

    private Long headImageId;

    /** 余分 */
    @Excel(name = "余分")

    private BigDecimal score;

    /** QQ号 */
    @Excel(name = "QQ号")

    private String qqNumber;

    /** 会员等级 */
    @Excel(name = "会员等级")

    private Long grade;

    /** 总流水 */
    @Excel(name = "总流水")

    private Long totalAmount;

    /** 权限 */
    @Excel(name = "权限")

    private String permission;

    /** 代理渠道id */
    @Excel(name = "代理渠道id")

    private Long channalId;

    /** 注册渠道id */
    @Excel(name = "注册渠道id")

    private Long registChannalId;
    /**
     * 用户拓展
     */
    private  WebMerchant extend;

    private String inviteCode;
    private String password1;

    private Long selectDeptId;
    public SysUser()
    {

    }

    public Date getLastScoreTime() {
        return lastScoreTime;
    }

    public void setLastScoreTime(Date lastScoreTime) {
        this.lastScoreTime = lastScoreTime;
    }

    public Long getSelectDeptId() {
        return selectDeptId;
    }

    public void setSelectDeptId(Long selectDeptId) {
        this.selectDeptId = selectDeptId;
    }

    public String getName(){
        return userName;
    }
    public String getPassword1() {
        return password1;
    }

    public int getRegisterIpRepeat() {
        return registerIpRepeat;
    }

    public void setRegisterIpRepeat(int registerIpRepeat) {
        this.registerIpRepeat = registerIpRepeat;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @Xss(message = "登录账号不能包含脚本字符")
    @NotBlank(message = "登录账号不能为空")
    @Size(min = 0, max = 30, message = "登录账号长度不能超过30个字符")
    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    @JsonIgnore
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @JsonIgnore
    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Date getPwdUpdateDate()
    {
        return pwdUpdateDate;
    }

    public void setPwdUpdateDate(Date pwdUpdateDate)
    {
        this.pwdUpdateDate = pwdUpdateDate;
    }

    public SysDept getDept()
    {
        if (dept == null)
        {
            dept = new SysDept();
        }
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    public List<SysRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRole> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public WebMerchant getExtend() {
        return extend;
    }

    public void setExtend(WebMerchant extend) {
        this.extend = extend;
    }

    public Long getHeadImageId() {
        return headImageId;
    }

    public void setHeadImageId(Long headImageId) {
        this.headImageId = headImageId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getChannalId() {
        return channalId;
    }

    public void setChannalId(Long channalId) {
        this.channalId = channalId;
    }

    public Long getRegistChannalId() {
        return registChannalId;
    }

    public void setRegistChannalId(Long registChannalId) {
        this.registChannalId = registChannalId;
    }

    @Override
    public String toString() {
        return getUserName();
    }


}
