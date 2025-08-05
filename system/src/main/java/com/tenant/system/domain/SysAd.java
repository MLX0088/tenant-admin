package com.tenant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 广告管理对象 sys_ad
 * 
 * @author luanyu
 * @date 2023-03-14
 */
public class SysAd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    private Long id;

    /** 图片链接 */
    @Excel(name = "图片链接")

    private String imgUrl;

    /** 跳转链接 */
    @Excel(name = "跳转链接")

    private String alink;

    /** 所属应用 */
    @Excel(name = "所属应用")

    private String appid;

    /** 位置 */
    @Excel(name = "位置")

    private String position;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date expireTime;
    /** 显示顺序 */
    @Excel(name = "显示顺序")

    private Long orderNum;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setAlink(String alink) 
    {
        this.alink = alink;
    }

    public String getAlink() 
    {
        return alink;
    }
    public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setExpireTime(Date expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() 
    {
        return expireTime;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("imgUrl", getImgUrl())
            .append("alink", getAlink())
            .append("appid", getAppid())
            .append("position", getPosition())
            .append("expireTime", getExpireTime())
            .toString();
    }
}
