package com.tenant.common.core.domain.entity;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * 商户拓展信息对象 t_web_merchant
 * 
 * @author 栾钰
 * @date 2023-02-17
 */
public class WebMerchant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long userId;

    /**  */
    @Excel(name = "")
    @NotEmpty(message = "支付宝姓名不能为空")
    private String aliPayName;
    /**  */
    @Excel(name = "")
    @NotEmpty(message = "支付宝账号不能为空")

    private String aliPayAccount;

    /**  */
    @Excel(name = "")

    private String qq;

    /**  */
    @Excel(name = "")

    private String weChat;

    /** 是否结算冻结 */
    @Excel(name = "是否结算冻结")

    private Integer settlementFreeze;

    /** 费率 */
    @Excel(name = "费率")

    private BigDecimal rate;

    /**  */
    @Excel(name = "")

    private String isShow;

    /** 微信openid */
    @Excel(name = "微信openid")

    private String openid;

    /** 是否通知 */
    @Excel(name = "是否通知")

    private String isNotify;

    /**  */
    @Excel(name = "")

    private String chatUrl;

    /** jeepay商户号 */
    @Excel(name = "jeepay商户号")

    private String mchNo;

    /** 支付宝用户id */
    @Excel(name = "支付宝用户id")

    private String zfbUserId;

    /**  */
    @Excel(name = "")

    private String appId;

    /** 私钥 */
    @Excel(name = "私钥")

    private String privateKey;

    /** 微信收付通是否开启 1是0否 */

    private String sftpay;

    /** 支付宝直付通 是否开启 1是 0否 */

    private String zftpay;
    /**
     * 主页url
     */
    private String homepageUrl;
    /**
     * 主页开关
     */
    private String homepageSwitch;
    /**
     * 主页二维码
     */
    private String homepageQR;
    /**
     * 排列方式
     */
    private String arrayMode;
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAliPayName(String aliPayName) 
    {
        this.aliPayName = aliPayName;
    }

    public String getAliPayName() 
    {
        return aliPayName;
    }
    public void setAliPayAccount(String aliPayAccount) 
    {
        this.aliPayAccount = aliPayAccount;
    }

    public String getAliPayAccount() 
    {
        return aliPayAccount;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setWeChat(String weChat) 
    {
        this.weChat = weChat;
    }

    public String getWeChat() 
    {
        return weChat;
    }
    public void setSettlementFreeze(Integer settlementFreeze) 
    {
        this.settlementFreeze = settlementFreeze;
    }

    public Integer getSettlementFreeze() 
    {
        return settlementFreeze;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
    }
    public void setIsShow(String isShow) 
    {
        this.isShow = isShow;
    }

    public String getIsShow() 
    {
        return isShow;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setIsNotify(String isNotify) 
    {
        this.isNotify = isNotify;
    }

    public String getIsNotify() 
    {
        return isNotify;
    }
    public void setChatUrl(String chatUrl) 
    {
        this.chatUrl = chatUrl;
    }

    public String getChatUrl() 
    {
        return chatUrl;
    }
    public void setMchNo(String mchNo) 
    {
        this.mchNo = mchNo;
    }

    public String getMchNo() 
    {
        return mchNo;
    }
    public void setZfbUserId(String zfbUserId) 
    {
        this.zfbUserId = zfbUserId;
    }

    public String getZfbUserId() 
    {
        return zfbUserId;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
    }
    public void setPrivateKey(String privateKey) 
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() 
    {
        return privateKey;
    }
    public void setSftpay(String sftpay) 
    {
        this.sftpay = sftpay;
    }

    public String getSftpay() 
    {
        return sftpay;
    }
    public void setZftpay(String zftpay) 
    {
        this.zftpay = zftpay;
    }

    public String getZftpay() 
    {
        return zftpay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("aliPayName", getAliPayName())
            .append("aliPayAccount", getAliPayAccount())
            .append("qq", getQq())
            .append("weChat", getWeChat())
            .append("settlementFreeze", getSettlementFreeze())
            .append("rate", getRate())
            .append("isShow", getIsShow())
            .append("openid", getOpenid())
            .append("isNotify", getIsNotify())
            .append("chatUrl", getChatUrl())
            .append("remark", getRemark())
            .append("mchNo", getMchNo())
            .append("zfbUserId", getZfbUserId())
            .append("appId", getAppId())
            .append("privateKey", getPrivateKey())
            .append("sftpay", getSftpay())
            .append("zftpay", getZftpay())
            .toString();
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getHomepageSwitch() {
        return homepageSwitch;
    }

    public void setHomepageSwitch(String homepageSwitch) {
        this.homepageSwitch = homepageSwitch;
    }

    public String getHomepageQR() {
        return homepageQR;
    }

    public void setHomepageQR(String homepageQR) {
        this.homepageQR = homepageQR;
    }

    public String getArrayMode() {
        return arrayMode;
    }

    public void setArrayMode(String arrayMode) {
        this.arrayMode = arrayMode;
    }
}
