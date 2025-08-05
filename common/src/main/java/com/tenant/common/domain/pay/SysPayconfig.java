package com.tenant.common.domain.pay;

import com.alipay.api.AlipayClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wechat.pay.java.core.Config;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

import java.util.Objects;

/**
 * 支付通道配置对象 sys_payconfig
 * 
 * @author 栾钰
 * @date 2023-02-20
 */
public class SysPayconfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */

    private Long id;

    /** 状态 */
    @Excel(name = "状态")

    private String status;

    /** 可支持的应用
     * 0小程序 1-PC 2h5
     * */
    @Excel(name = "可支持的应用")

    private String appType;

    /** 支付类型 */
    @Excel(name = "支付类型")

    private String payType;

    /** 应用id */
    @Excel(name = "应用id")

    private String appID;

    /** 微信支付商户号 */
    @Excel(name = "微信支付商户号")

    private String wxpayMchno;

    /** 公众号AppSecret */
    @Excel(name = "公众号AppSecret")

    private String wxpayAppsecret;

    /** 微信api版本 */
    @Excel(name = "微信api版本")

    private String wxpayVersion;

    /** apiv2密钥 */

    private String wxpayApiv2key;

    /** apiv3密钥 */

    private String wxpayApiv3key;

    /** 序列号 */
    @Excel(name = "序列号")

    private String wxpayMerchantserialnumber;

    /** 证书文件wechatPayCertificatePath
     *  微信支付平台证书路径
     * */
    @Excel(name = "证书文件wechatPayCertificatePath")

    private String wxpayApiclientCertpem;

    /** 私钥文件privateKeyPath
     * 商户API私钥路径
     * */
    @Excel(name = "私钥文件privateKeyPath")

    private String wxpayApiclientKeypem;

    /** 是否使用证书 */
    @Excel(name = "是否使用证书")

    private String alipayIsusecert;

    /** 支付宝应用私钥 */

    private String alipayAppkey;

    /** 支付宝公钥 */

    private String alipayPublickey;

    /** 应用公钥证书 */
    @Excel(name = "应用公钥证书")

    private String alipayAppcertpublickey;

    /** 支付宝公钥证书 */
    @Excel(name = "支付宝公钥证书")

    private String alipayAlipaycertpublickey;

    /** 支付宝根证书 */
    @Excel(name = "支付宝根证书")

    private String alipayAlipayrootcert;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 支付宝
     */
    @JsonIgnore
    private AlipayClient alipayClient;
    /**
     * 微信
     */
    @JsonIgnore
    private Config config;
    public enum PayType
    {
        /** 微信  */
        WECHAT("0"),
        /** 支付宝 */
        ALIPAY("1");
        private final String value;

        PayType(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return this.value;
        }
    }
    public enum AppType
    {
        /** 小程序 */
        APPLET("0"),
        /** PC */
        PC("1"),
        /**
         * h5
         */
        H5("2");
        private final String value;

        AppType(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return this.value;
        }
    }
    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAppType(String appType) 
    {
        this.appType = appType;
    }

    public String getAppType() 
    {
        return appType;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setAppID(String appID) 
    {
        this.appID = appID;
    }

    public String getAppID() 
    {
        return appID;
    }
    public void setWxpayMchno(String wxpayMchno) 
    {
        this.wxpayMchno = wxpayMchno;
    }

    public String getWxpayMchno() 
    {
        return wxpayMchno;
    }
    public void setWxpayAppsecret(String wxpayAppsecret) 
    {
        this.wxpayAppsecret = wxpayAppsecret;
    }

    public String getWxpayAppsecret() 
    {
        return wxpayAppsecret;
    }
    public void setWxpayVersion(String wxpayVersion) 
    {
        this.wxpayVersion = wxpayVersion;
    }

    public String getWxpayVersion() 
    {
        return wxpayVersion;
    }
    public void setWxpayApiv2key(String wxpayApiv2key) 
    {
        this.wxpayApiv2key = wxpayApiv2key;
    }

    public String getWxpayApiv2key() 
    {
        return wxpayApiv2key;
    }
    public void setWxpayApiv3key(String wxpayApiv3key) 
    {
        this.wxpayApiv3key = wxpayApiv3key;
    }

    public String getWxpayApiv3key() 
    {
        return wxpayApiv3key;
    }
    public void setWxpayMerchantserialnumber(String wxpayMerchantserialnumber) 
    {
        this.wxpayMerchantserialnumber = wxpayMerchantserialnumber;
    }

    public String getWxpayMerchantserialnumber() 
    {
        return wxpayMerchantserialnumber;
    }
    public void setWxpayApiclientCertpem(String wxpayApiclientCertpem) 
    {
        this.wxpayApiclientCertpem = wxpayApiclientCertpem;
    }

    public String getWxpayApiclientCertpem() 
    {
        return wxpayApiclientCertpem;
    }
    public void setWxpayApiclientKeypem(String wxpayApiclientKeypem) 
    {
        this.wxpayApiclientKeypem = wxpayApiclientKeypem;
    }

    public String getWxpayApiclientKeypem() 
    {
        return wxpayApiclientKeypem;
    }
    public void setAlipayIsusecert(String alipayIsusecert) 
    {
        this.alipayIsusecert = alipayIsusecert;
    }

    public String getAlipayIsusecert() 
    {
        return alipayIsusecert;
    }
    public void setAlipayAppkey(String alipayAppkey) 
    {
        this.alipayAppkey = alipayAppkey;
    }

    public String getAlipayAppkey() 
    {
        return alipayAppkey;
    }
    public void setAlipayPublickey(String alipayPublickey) 
    {
        this.alipayPublickey = alipayPublickey;
    }

    public String getAlipayPublickey() 
    {
        return alipayPublickey;
    }
    public void setAlipayAppcertpublickey(String alipayAppcertpublickey) 
    {
        this.alipayAppcertpublickey = alipayAppcertpublickey;
    }

    public String getAlipayAppcertpublickey() 
    {
        return alipayAppcertpublickey;
    }
    public void setAlipayAlipaycertpublickey(String alipayAlipaycertpublickey) 
    {
        this.alipayAlipaycertpublickey = alipayAlipaycertpublickey;
    }

    public String getAlipayAlipaycertpublickey() 
    {
        return alipayAlipaycertpublickey;
    }
    public void setAlipayAlipayrootcert(String alipayAlipayrootcert) 
    {
        this.alipayAlipayrootcert = alipayAlipayrootcert;
    }

    public String getAlipayAlipayrootcert() 
    {
        return alipayAlipayrootcert;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("appType", getAppType())
            .append("payType", getPayType())
            .append("remark", getRemark())
            .append("appID", getAppID())
            .append("wxpayMchno", getWxpayMchno())
            .append("wxpayAppsecret", getWxpayAppsecret())
            .append("wxpayVersion", getWxpayVersion())
            .append("wxpayApiv2key", getWxpayApiv2key())
            .append("wxpayApiv3key", getWxpayApiv3key())
            .append("wxpayMerchantserialnumber", getWxpayMerchantserialnumber())
            .append("wxpayApiclientCertpem", getWxpayApiclientCertpem())
            .append("wxpayApiclientKeypem", getWxpayApiclientKeypem())
            .append("alipayIsusecert", getAlipayIsusecert())
            .append("alipayAppkey", getAlipayAppkey())
            .append("alipayPublickey", getAlipayPublickey())
            .append("alipayAppcertpublickey", getAlipayAppcertpublickey())
            .append("alipayAlipaycertpublickey", getAlipayAlipaycertpublickey())
            .append("alipayAlipayrootcert", getAlipayAlipayrootcert())
            .append("createTime", getCreateTime())
            .toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPayconfig that = (SysPayconfig) o;
        return appID.equals(that.appID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appID);
    }
}
