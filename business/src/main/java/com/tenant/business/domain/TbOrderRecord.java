package com.tenant.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 订单记录对象 tb_order_record
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbOrderRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")

    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")

    private String userName;

    /** 财务类型 */
    @Excel(name = "财务类型")

    private Long type;

    /** 支付类型 */
    @Excel(name = "支付类型")

    private String payType;

    /** 上次来款名/支付人名字 */
    @Excel(name = "姓名")

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

    /** 分数 */
    @Excel(name = "分数")

    private BigDecimal score;

    /** 当前余分 */
    @Excel(name = "当前余分")

    private BigDecimal leftScore;

    /** 是否同名 */
    @Excel(name = "是否同名")

    private String isSame;

    /** 审核人id */
    @Excel(name = "审核人id")

    private Long auditUserId;

    /** 审核人 */
    @Excel(name = "审核人")

    private String auditUserName;

    /** 状态 */
    @Excel(name = "状态")

    private Integer status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date auditTime;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;
    private String lastPayName;
    private Long vipAccountId;

    public String getLastPayName() {
        return lastPayName;
    }

    public void setLastPayName(String lastPayName) {
        this.lastPayName = lastPayName;
    }

    public Long getVipAccountId() {
        return vipAccountId;
    }

    public void setVipAccountId(Long vipAccountId) {
        this.vipAccountId = vipAccountId;
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
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
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
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }
    public void setLeftScore(BigDecimal leftScore) 
    {
        this.leftScore = leftScore;
    }

    public BigDecimal getLeftScore() 
    {
        return leftScore;
    }
    public void setIsSame(String isSame) 
    {
        this.isSame = isSame;
    }

    public String getIsSame() 
    {
        return isSame;
    }
    public void setAuditUserId(Long auditUserId) 
    {
        this.auditUserId = auditUserId;
    }

    public Long getAuditUserId() 
    {
        return auditUserId;
    }
    public void setAuditUserName(String auditUserName) 
    {
        this.auditUserName = auditUserName;
    }

    public String getAuditUserName() 
    {
        return auditUserName;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
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
            .append("userName", getUserName())
            .append("type", getType())
            .append("payType", getPayType())
            .append("name", getName())
            .append("account", getAccount())
            .append("imageId", getImageId())
            .append("bankName", getBankName())
            .append("score", getScore())
            .append("leftScore", getLeftScore())
            .append("isSame", getIsSame())
            .append("auditUserId", getAuditUserId())
            .append("auditUserName", getAuditUserName())
            .append("status", getStatus())
            .append("auditTime", getAuditTime())
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
