package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 手动开奖记录对象 tb_draw_manual
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbDrawManual extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间 */
    @Excel(name = "房间")

    private String roomName;

    /** 期号 */
    @Excel(name = "期号")

    private String periodNumber;

    /** 第一码 */
    @Excel(name = "第一码")

    private Long first;

    /** 第二码 */
    @Excel(name = "第二码")

    private Long second;

    /** 第三码 */
    @Excel(name = "第三码")

    private Long third;

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
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setPeriodNumber(String periodNumber) 
    {
        this.periodNumber = periodNumber;
    }

    public String getPeriodNumber() 
    {
        return periodNumber;
    }
    public void setFirst(Long first) 
    {
        this.first = first;
    }

    public Long getFirst() 
    {
        return first;
    }
    public void setSecond(Long second) 
    {
        this.second = second;
    }

    public Long getSecond() 
    {
        return second;
    }
    public void setThird(Long third) 
    {
        this.third = third;
    }

    public Long getThird() 
    {
        return third;
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
            .append("roomName", getRoomName())
            .append("periodNumber", getPeriodNumber())
            .append("first", getFirst())
            .append("second", getSecond())
            .append("third", getThird())
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
