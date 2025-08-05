package com.tenant.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * 短链对象 url_map
 * 
 * @author 栾钰
 * @date 2023-03-07
 */
public class UrlMap extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    private Long id;

    /** 短链接 */
    @Excel(name = "短链接")
    @NotEmpty(message = "短链接不能为空")

    private String surl;

    /** 长链接 */
    @Excel(name = "长链接")
    @NotEmpty(message = "长链接不能为空")

    private String lurl;

    /** 访问次数 */
    @Excel(name = "访问次数")

    private Long views;

    /** 用户id */
    @Excel(name = "用户id")

    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSurl(String surl) 
    {
        this.surl = surl;
    }

    public String getSurl() 
    {
        return surl;
    }
    public void setLurl(String lurl) 
    {
        this.lurl = lurl;
    }

    public String getLurl() 
    {
        return lurl;
    }
    public void setViews(Long views) 
    {
        this.views = views;
    }

    public Long getViews() 
    {
        return views;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("surl", getSurl())
            .append("lurl", getLurl())
            .append("views", getViews())
            .append("createTime", getCreateTime())
            .append("userId", getUserId())
            .toString();
    }
}
