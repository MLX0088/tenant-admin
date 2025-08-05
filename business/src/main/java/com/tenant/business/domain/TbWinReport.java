package com.tenant.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 盈亏信息对象 tb_win_report
 * 
 * @author luanyu
 * @date 2025-05-05
 */
public class TbWinReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    private Long id;

    /** 数据表日期 */
    @Excel(name = "数据表日期")

    private String genDate;

    /** 新增人数 */
    @Excel(name = "新增人数")

    private Integer addPeople;

    /** 在线分 */
    @Excel(name = "在线分")

    private BigDecimal totalScore;

    /** 玩家人数 */
    @Excel(name = "玩家人数")

    private Integer userCount;

    /** 上分量 */
    @Excel(name = "上分量")

    private Long addScore;

    /** 下分量 */
    @Excel(name = "下分量")

    private Long minusScore;

    /** 送分量 */
    @Excel(name = "送分量")

    private BigDecimal otherScore;

    /** 实际盈亏分 */
    @Excel(name = "实际盈亏分")

    private Long actualWinScore;

    /** 盈亏分 */
    @Excel(name = "盈亏分")

    private BigDecimal winScore;

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
    public void setGenDate(String genDate) 
    {
        this.genDate = genDate;
    }

    public String getGenDate() 
    {
        return genDate;
    }
    public void setAddPeople(Integer addPeople) 
    {
        this.addPeople = addPeople;
    }

    public Integer getAddPeople() 
    {
        return addPeople;
    }
    public void setTotalScore(BigDecimal totalScore) 
    {
        this.totalScore = totalScore;
    }

    public BigDecimal getTotalScore() 
    {
        return totalScore;
    }
    public void setUserCount(Integer userCount) 
    {
        this.userCount = userCount;
    }

    public Integer getUserCount() 
    {
        return userCount;
    }
    public void setAddScore(Long addScore) 
    {
        this.addScore = addScore;
    }

    public Long getAddScore() 
    {
        return addScore;
    }
    public void setMinusScore(Long minusScore) 
    {
        this.minusScore = minusScore;
    }

    public Long getMinusScore() 
    {
        return minusScore;
    }
    public void setOtherScore(BigDecimal otherScore) 
    {
        this.otherScore = otherScore;
    }

    public BigDecimal getOtherScore() 
    {
        return otherScore;
    }
    public void setActualWinScore(Long actualWinScore) 
    {
        this.actualWinScore = actualWinScore;
    }

    public Long getActualWinScore() 
    {
        return actualWinScore;
    }
    public void setWinScore(BigDecimal winScore) 
    {
        this.winScore = winScore;
    }

    public BigDecimal getWinScore() 
    {
        return winScore;
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
            .append("genDate", getGenDate())
            .append("addPeople", getAddPeople())
            .append("totalScore", getTotalScore())
            .append("userCount", getUserCount())
            .append("addScore", getAddScore())
            .append("minusScore", getMinusScore())
            .append("otherScore", getOtherScore())
            .append("actualWinScore", getActualWinScore())
            .append("winScore", getWinScore())
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
