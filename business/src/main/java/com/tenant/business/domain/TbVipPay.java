package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 会员支付信息对象 tb_vip_pay
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbVipPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 用户ID */

    private Long userId;

    /** 支付类型 */
    @Excel(name = "支付类型")

    private String payType;

    /** 名字 */
    @Excel(name = "名字")

    private String name;

    /** 账户 */
    @Excel(name = "账户")

    private String account;

    /** 二维码 */
    @Excel(name = "二维码")

    private Long imageId;

    /** 户行 */
    @Excel(name = "户行/微信昵称")

    private String bankName;
    @Excel(name = "开户行")
    private String openBankName;

    /** 是否同名 */
    @Excel(name = "是否同名")

    private String isSame;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setIsSame(String isSame) 
    {
        this.isSame = isSame;
    }

    public String getIsSame() 
    {
        return isSame;
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
            .append("userId", getUserId())
            .append("payType", getPayType())
            .append("name", getName())
            .append("account", getAccount())
            .append("imageId", getImageId())
            .append("bankName", getBankName())
            .append("isSame", getIsSame())
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
