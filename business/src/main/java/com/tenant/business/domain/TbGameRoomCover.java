package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 游戏房间封面对象 tb_game_room_cover
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbGameRoomCover extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间类型 */
    @Excel(name = "房间类型")

    private String type;

    /** 封面图 */
    @Excel(name = "封面图")

    private Long coverImagesId;

    /** 赔率提示 */
    @Excel(name = "赔率提示")

    private String rateTips;

    /** 赔率说明 */
    @Excel(name = "赔率说明")

    private String rateDesc;

    /** 活动内容 */
    @Excel(name = "活动内容")

    private String eventContent;

    /** 回红说明 */
    @Excel(name = "回红说明")

    private String commissionDesc;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;
    private String imageUrl;
    private boolean maintain;

    public boolean isMaintain() {
        return maintain;
    }

    public void setMaintain(boolean maintain) {
        this.maintain = maintain;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCoverImagesId(Long coverImagesId) 
    {
        this.coverImagesId = coverImagesId;
    }

    public Long getCoverImagesId() 
    {
        return coverImagesId;
    }
    public void setRateTips(String rateTips) 
    {
        this.rateTips = rateTips;
    }

    public String getRateTips() 
    {
        return rateTips;
    }
    public void setRateDesc(String rateDesc) 
    {
        this.rateDesc = rateDesc;
    }

    public String getRateDesc() 
    {
        return rateDesc;
    }
    public void setEventContent(String eventContent) 
    {
        this.eventContent = eventContent;
    }

    public String getEventContent() 
    {
        return eventContent;
    }
    public void setCommissionDesc(String commissionDesc) 
    {
        this.commissionDesc = commissionDesc;
    }

    public String getCommissionDesc() 
    {
        return commissionDesc;
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
            .append("type", getType())
            .append("coverImagesId", getCoverImagesId())
            .append("rateTips", getRateTips())
            .append("rateDesc", getRateDesc())
            .append("eventContent", getEventContent())
            .append("commissionDesc", getCommissionDesc())
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
