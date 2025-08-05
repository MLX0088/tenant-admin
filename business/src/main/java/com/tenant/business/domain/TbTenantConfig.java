package com.tenant.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 租客基础设置对象 tb_tenant_config
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbTenantConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 登录验证码 */
    @Excel(name = "登录验证码")

    private String needLoginCode;

    /** 注册邀请 */
    @Excel(name = "注册邀请")

    private String canInvite;

    /** 邀请码 */
    @Excel(name = "邀请码")

    private String inviteCode;

    /** 短信验证码 */
    @Excel(name = "短信验证码")

    private String needSmsCode;

    /** 机器人头像 */
    @Excel(name = "机器人头像")

    private Long rebotImageId;

    /** APP名字 */
    @Excel(name = "APP名字")

    private String appName;

    /** 机器人名字 */
    @Excel(name = "机器人名字")

    private String rebotName;

    /** 客服名字 */
    @Excel(name = "客服名字")

    private String csName;

    /** 公告弹窗 */
    @Excel(name = "公告弹窗")

    private String popAction;

    /** 开奖信息人数排列 */
    @Excel(name = "开奖信息人数排列")

    private Long arrangement;

    /** 版本号 */
    @Excel(name = "版本号")

    private String version;

    /** 最低上分 */
    @Excel(name = "最低上分")

    private Long minAddScore;

    /** 最低下分 */
    @Excel(name = "最低下分")

    private Long minMinusScore;

    /** 满把下分 */
    @Excel(name = "满把下分")

    private Long fullMinusScore;

    /** 用户每天下分次数 */
    @Excel(name = "用户每天下分次数")

    private Long maxMinusCount;

    /** 限时上分 */
    @Excel(name = "限时上分")

    private String isLimitAdd;

    /** 开启时间 */

    private String limitAddStarttime;

    /** 结束时间 */

    private String limitAddEndtime;

    /** 限时上分备注 */
    @Excel(name = "限时上分备注")

    private String limitAddRemark;

    /** 限时下分（0开启 1关闭） */
    @Excel(name = "限时下分", readConverterExp = "0=开启,1=关闭")

    private String isLimitMinus;

    /** 开启时间 */

    private String limitMinusStarttime;

    /** 结束时间 */

    private String limitMinusEndtime;

    /** 限时下分备注 */
    @Excel(name = "限时下分备注")

    private String limitMinusRemark;

    /** 用户二次修改提现信息（0支持 1禁止）默认禁止 */
    @Excel(name = "用户二次修改提现信息", readConverterExp = "0=支持,1=禁止")

    private String allowChangeWithdraw;

    /** 绑定提现才能看到上分账户（0是 1否） */
    @Excel(name = "绑定提现才能看到上分账户", readConverterExp = "0=是,1=否")

    private String associationWithdraw;

    /** 单注最低投注 */
    @Excel(name = "单注最低投注")

    private Long minScore;

    /** 是否开启转盘 */
    @Excel(name = "是否开启转盘")

    private String openTurntable;

    /** 转盘流水阈值 */
    @Excel(name = "转盘流水阈值")

    private Long turntableScore;

    /** 是否显示刮刮乐 */
    @Excel(name = "是否显示刮刮乐")

    private String showScratchCard;

    /** 站外客服 */
    @Excel(name = "站外客服")

    private String offSiteCs;

    /** 用户私聊第一句 */
    @Excel(name = "用户私聊第一句")

    private String chatSayHello;

    /** 介绍红 */
    @Excel(name = "介绍红")

    private String introduceCommission;

    /** 客户端链接 */
    @Excel(name = "客户端链接")

    private String clientUrl;

    /** H5导航链接 */
    @Excel(name = "H5导航链接")

    private String navUrl;

    /** 关盘段落 */
    @Excel(name = "关盘段落")

    private String closeSection;

    /** 关于我们 */
    @Excel(name = "关于我们")

    private String aboutUs;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;
    private String turntableRule;
    private String vipGradeDesc;
    private String androidUrl;
    private String iosUrl;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public String getTurntableRule() {
        return turntableRule;
    }

    public void setTurntableRule(String turntableRule) {
        this.turntableRule = turntableRule;
    }

    public String getVipGradeDesc() {
        return vipGradeDesc;
    }

    public void setVipGradeDesc(String vipGradeDesc) {
        this.vipGradeDesc = vipGradeDesc;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNeedLoginCode(String needLoginCode) 
    {
        this.needLoginCode = needLoginCode;
    }

    public String getNeedLoginCode() 
    {
        return needLoginCode;
    }
    public void setCanInvite(String canInvite) 
    {
        this.canInvite = canInvite;
    }

    public String getCanInvite() 
    {
        return canInvite;
    }
    public void setInviteCode(String inviteCode) 
    {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() 
    {
        return inviteCode;
    }
    public void setNeedSmsCode(String needSmsCode) 
    {
        this.needSmsCode = needSmsCode;
    }

    public String getNeedSmsCode() 
    {
        return needSmsCode;
    }
    public void setRebotImageId(Long rebotImageId) 
    {
        this.rebotImageId = rebotImageId;
    }

    public Long getRebotImageId() 
    {
        return rebotImageId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setRebotName(String rebotName) 
    {
        this.rebotName = rebotName;
    }

    public String getRebotName() 
    {
        return rebotName;
    }
    public void setCsName(String csName) 
    {
        this.csName = csName;
    }

    public String getCsName() 
    {
        return csName;
    }
    public void setPopAction(String popAction) 
    {
        this.popAction = popAction;
    }

    public String getPopAction() 
    {
        return popAction;
    }
    public void setArrangement(Long arrangement) 
    {
        this.arrangement = arrangement;
    }

    public Long getArrangement() 
    {
        return arrangement;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setMinAddScore(Long minAddScore) 
    {
        this.minAddScore = minAddScore;
    }

    public Long getMinAddScore() 
    {
        return minAddScore;
    }
    public void setMinMinusScore(Long minMinusScore) 
    {
        this.minMinusScore = minMinusScore;
    }

    public Long getMinMinusScore() 
    {
        return minMinusScore;
    }
    public void setFullMinusScore(Long fullMinusScore) 
    {
        this.fullMinusScore = fullMinusScore;
    }

    public Long getFullMinusScore() 
    {
        return fullMinusScore;
    }
    public void setMaxMinusCount(Long maxMinusCount) 
    {
        this.maxMinusCount = maxMinusCount;
    }

    public Long getMaxMinusCount() 
    {
        return maxMinusCount;
    }
    public void setIsLimitAdd(String isLimitAdd) 
    {
        this.isLimitAdd = isLimitAdd;
    }

    public String getIsLimitAdd() 
    {
        return isLimitAdd;
    }
    public void setLimitAddStarttime(String limitAddStarttime)
    {
        this.limitAddStarttime = limitAddStarttime;
    }

    public String getLimitAddStarttime()
    {
        return limitAddStarttime;
    }
    public void setLimitAddEndtime(String limitAddEndtime)
    {
        this.limitAddEndtime = limitAddEndtime;
    }

    public String getLimitAddEndtime()
    {
        return limitAddEndtime;
    }
    public void setLimitAddRemark(String limitAddRemark) 
    {
        this.limitAddRemark = limitAddRemark;
    }

    public String getLimitAddRemark() 
    {
        return limitAddRemark;
    }
    public void setIsLimitMinus(String isLimitMinus) 
    {
        this.isLimitMinus = isLimitMinus;
    }

    public String getIsLimitMinus() 
    {
        return isLimitMinus;
    }
    public void setLimitMinusStarttime(String limitMinusStarttime)
    {
        this.limitMinusStarttime = limitMinusStarttime;
    }

    public String getLimitMinusStarttime()
    {
        return limitMinusStarttime;
    }
    public void setLimitMinusEndtime(String limitMinusEndtime)
    {
        this.limitMinusEndtime = limitMinusEndtime;
    }

    public String getLimitMinusEndtime()
    {
        return limitMinusEndtime;
    }
    public void setLimitMinusRemark(String limitMinusRemark) 
    {
        this.limitMinusRemark = limitMinusRemark;
    }

    public String getLimitMinusRemark() 
    {
        return limitMinusRemark;
    }
    public void setAllowChangeWithdraw(String allowChangeWithdraw) 
    {
        this.allowChangeWithdraw = allowChangeWithdraw;
    }

    public String getAllowChangeWithdraw() 
    {
        return allowChangeWithdraw;
    }
    public void setAssociationWithdraw(String associationWithdraw) 
    {
        this.associationWithdraw = associationWithdraw;
    }

    public String getAssociationWithdraw() 
    {
        return associationWithdraw;
    }
    public void setMinScore(Long minScore) 
    {
        this.minScore = minScore;
    }

    public Long getMinScore() 
    {
        return minScore;
    }
    public void setOpenTurntable(String openTurntable) 
    {
        this.openTurntable = openTurntable;
    }

    public String getOpenTurntable() 
    {
        return openTurntable;
    }
    public void setTurntableScore(Long turntableScore) 
    {
        this.turntableScore = turntableScore;
    }

    public Long getTurntableScore() 
    {
        return turntableScore;
    }
    public void setShowScratchCard(String showScratchCard) 
    {
        this.showScratchCard = showScratchCard;
    }

    public String getShowScratchCard() 
    {
        return showScratchCard;
    }
    public void setOffSiteCs(String offSiteCs) 
    {
        this.offSiteCs = offSiteCs;
    }

    public String getOffSiteCs() 
    {
        return offSiteCs;
    }
    public void setChatSayHello(String chatSayHello) 
    {
        this.chatSayHello = chatSayHello;
    }

    public String getChatSayHello() 
    {
        return chatSayHello;
    }
    public void setIntroduceCommission(String introduceCommission) 
    {
        this.introduceCommission = introduceCommission;
    }

    public String getIntroduceCommission() 
    {
        return introduceCommission;
    }
    public void setClientUrl(String clientUrl) 
    {
        this.clientUrl = clientUrl;
    }

    public String getClientUrl() 
    {
        return clientUrl;
    }
    public void setNavUrl(String navUrl) 
    {
        this.navUrl = navUrl;
    }

    public String getNavUrl() 
    {
        return navUrl;
    }
    public void setCloseSection(String closeSection) 
    {
        this.closeSection = closeSection;
    }

    public String getCloseSection() 
    {
        return closeSection;
    }
    public void setAboutUs(String aboutUs) 
    {
        this.aboutUs = aboutUs;
    }

    public String getAboutUs() 
    {
        return aboutUs;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setTenantId(Long tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId() 
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("needLoginCode", getNeedLoginCode())
            .append("canInvite", getCanInvite())
            .append("inviteCode", getInviteCode())
            .append("needSmsCode", getNeedSmsCode())
            .append("rebotImageId", getRebotImageId())
            .append("appName", getAppName())
            .append("rebotName", getRebotName())
            .append("csName", getCsName())
            .append("popAction", getPopAction())
            .append("arrangement", getArrangement())
            .append("version", getVersion())
            .append("minAddScore", getMinAddScore())
            .append("minMinusScore", getMinMinusScore())
            .append("fullMinusScore", getFullMinusScore())
            .append("maxMinusCount", getMaxMinusCount())
            .append("isLimitAdd", getIsLimitAdd())
            .append("limitAddStarttime", getLimitAddStarttime())
            .append("limitAddEndtime", getLimitAddEndtime())
            .append("limitAddRemark", getLimitAddRemark())
            .append("isLimitMinus", getIsLimitMinus())
            .append("limitMinusStarttime", getLimitMinusStarttime())
            .append("limitMinusEndtime", getLimitMinusEndtime())
            .append("limitMinusRemark", getLimitMinusRemark())
            .append("allowChangeWithdraw", getAllowChangeWithdraw())
            .append("associationWithdraw", getAssociationWithdraw())
            .append("minScore", getMinScore())
            .append("openTurntable", getOpenTurntable())
            .append("turntableScore", getTurntableScore())
            .append("showScratchCard", getShowScratchCard())
            .append("offSiteCs", getOffSiteCs())
            .append("chatSayHello", getChatSayHello())
            .append("introduceCommission", getIntroduceCommission())
            .append("clientUrl", getClientUrl())
            .append("navUrl", getNavUrl())
            .append("closeSection", getCloseSection())
            .append("aboutUs", getAboutUs())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .toString();
    }
}
