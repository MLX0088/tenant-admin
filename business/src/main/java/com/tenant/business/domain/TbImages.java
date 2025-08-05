package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 图片管理对象 tb_images
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbImages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 图片字节流 */
    @Excel(name = "图片字节流")

    private byte[] imageData;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */
    @Excel(name = "租户id")

    private Long tenantId;

    /** 0是头像 1不是头像 */
    @Excel(name = "0是头像 1不是头像")

    private Integer isHead;
    private String url;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImageData(byte[] imageData)
    {
        this.imageData = imageData;
    }

    public byte[] getImageData()
    {
        return imageData;
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
    public void setIsHead(Integer isHead) 
    {
        this.isHead = isHead;
    }

    public Integer getIsHead() 
    {
        return isHead;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imageData", getImageData())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("isHead", getIsHead())
            .toString();
    }
}
