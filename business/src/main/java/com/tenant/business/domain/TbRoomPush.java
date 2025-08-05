package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 房间推送信息对象 tb_room_push
 * 
 * @author luanyu
 * @date 2025-05-06
 */
public class TbRoomPush extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间配置表 */
    @Excel(name = "房间配置表")

    private Long roomConfigId;

    /** 前几秒推送 */
    @Excel(name = "前几秒推送")

    private Long second;

    /** 推送内容 */
    @Excel(name = "推送内容")

    private String content;

    /** 删除标志（0存在 2删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;

    /** 房间名称 */

    private String roomName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomConfigId(Long roomConfigId) 
    {
        this.roomConfigId = roomConfigId;
    }

    public Long getRoomConfigId() 
    {
        return roomConfigId;
    }
    public void setSecond(Long second) 
    {
        this.second = second;
    }

    public Long getSecond() 
    {
        return second;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomConfigId", getRoomConfigId())
            .append("second", getSecond())
            .append("content", getContent())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("roomName", getRoomName())
            .toString();
    }
}
