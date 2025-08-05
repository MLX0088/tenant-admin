package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 上/下分账号设置对象 tb_account_config
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbAccountConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 支付类型 */
    @Excel(name = "支付类型")

    private String payType;

    /** 户名 */
    @Excel(name = "户名")

    private String name;

    /** 账户 */
    @Excel(name = "账户")

    private String account;

    /** 二维码 */
    @Excel(name = "二维码")

    private Long imageId;

    /** 户行 */
    @Excel(name = "户行")

    private String bankName;

    /** 额度分配 */
    @Excel(name = "额度分配")

    private Long quota;

    /** 上分/下分 */
    @Excel(name = "上分/下分")

    private String isAddMinus;

    /** 状态 */
    @Excel(name = "状态")

    private String status;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setQuota(Long quota) 
    {
        this.quota = quota;
    }

    public Long getQuota() 
    {
        return quota;
    }
    public void setIsAddMinus(String isAddMinus) 
    {
        this.isAddMinus = isAddMinus;
    }

    public String getIsAddMinus() 
    {
        return isAddMinus;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("payType", getPayType())
            .append("name", getName())
            .append("account", getAccount())
            .append("imageId", getImageId())
            .append("bankName", getBankName())
            .append("quota", getQuota())
            .append("isAddMinus", getIsAddMinus())
            .append("status", getStatus())
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
