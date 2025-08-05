package com.tenant.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 会员信息对象 tb_vip_info
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbVipInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")

    private Long userId;

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

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */
    @Excel(name = "租户id")

    private Long tenantId;

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
    public void setHeadImageId(Long headImageId) 
    {
        this.headImageId = headImageId;
    }

    public Long getHeadImageId() 
    {
        return headImageId;
    }
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }
    public void setQqNumber(String qqNumber) 
    {
        this.qqNumber = qqNumber;
    }

    public String getQqNumber() 
    {
        return qqNumber;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }
    public void setTotalAmount(Long totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmount() 
    {
        return totalAmount;
    }
    public void setPermission(String permission) 
    {
        this.permission = permission;
    }

    public String getPermission() 
    {
        return permission;
    }
    public void setChannalId(Long channalId) 
    {
        this.channalId = channalId;
    }

    public Long getChannalId() 
    {
        return channalId;
    }
    public void setRegistChannalId(Long registChannalId) 
    {
        this.registChannalId = registChannalId;
    }

    public Long getRegistChannalId() 
    {
        return registChannalId;
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
            .append("headImageId", getHeadImageId())
            .append("score", getScore())
            .append("qqNumber", getQqNumber())
            .append("grade", getGrade())
            .append("totalAmount", getTotalAmount())
            .append("permission", getPermission())
            .append("channalId", getChannalId())
            .append("registChannalId", getRegistChannalId())
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
