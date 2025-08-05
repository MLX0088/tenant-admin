package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 游戏类型封面对象 tb_game_cover
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbGameCover extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 游戏类型 */
    @Excel(name = "游戏类型")

    private String type;

    /** 封面图 */
    @Excel(name = "封面图")

    private Long coverImagesId;

    /** 游戏提示一 */
    @Excel(name = "游戏提示一")

    private String tips1;

    /** 游戏提示二 */
    @Excel(name = "游戏提示二")

    private String tips2;

    /** 游戏介绍 */
    @Excel(name = "游戏介绍")

    private String introduce;

    /** 排序 */
    @Excel(name = "排序")

    private Long sort;

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
    public void setTips1(String tips1) 
    {
        this.tips1 = tips1;
    }

    public String getTips1() 
    {
        return tips1;
    }
    public void setTips2(String tips2) 
    {
        this.tips2 = tips2;
    }

    public String getTips2() 
    {
        return tips2;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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
            .append("tips1", getTips1())
            .append("tips2", getTips2())
            .append("introduce", getIntroduce())
            .append("sort", getSort())
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
