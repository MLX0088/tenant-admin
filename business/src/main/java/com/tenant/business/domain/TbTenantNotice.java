package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 租户公告管理对象 tb_tenant_notice
 * 
 * @author luanyu
 * @date 2025-05-08
 */
public class TbTenantNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 标题 */
    @Excel(name = "标题")

    private String title;

    /** 内容 */
    @Excel(name = "内容")

    private String content;

    /** 弹窗公告（0是 1否） */
    @Excel(name = "弹窗公告", readConverterExp = "0=是,1=否")

    private String isPop;

    /** 轮播图片 */
    @Excel(name = "轮播图片")

    private Long rotatingImagesId;

    /** 排序 */
    @Excel(name = "排序")

    private Long sort;

    /** 公告类型（0APP公告 1导航网轮播 2导航网公告 3首页轮播图） */
    @Excel(name = "公告类型", readConverterExp = "0=APP公告,1=导航网轮播,2=导航网公告,3=首页轮播图")

    private Long type;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */
    @Excel(name = "租户id")

    private Long tenantId;

    /** 是否首页轮播图 */
    @Excel(name = "是否首页轮播图")

    private String isRotating;
    private String imageUrl;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setIsPop(String isPop) 
    {
        this.isPop = isPop;
    }

    public String getIsPop() 
    {
        return isPop;
    }
    public void setRotatingImagesId(Long rotatingImagesId) 
    {
        this.rotatingImagesId = rotatingImagesId;
    }

    public Long getRotatingImagesId() 
    {
        return rotatingImagesId;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
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
    public void setIsRotating(String isRotating) 
    {
        this.isRotating = isRotating;
    }

    public String getIsRotating() 
    {
        return isRotating;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("isPop", getIsPop())
            .append("rotatingImagesId", getRotatingImagesId())
            .append("sort", getSort())
            .append("type", getType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("isRotating", getIsRotating())
            .toString();
    }
}
