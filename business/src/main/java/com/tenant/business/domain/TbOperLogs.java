package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 操作记录对象 tb_oper_logs
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbOperLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 操作对象ID */
    @Excel(name = "操作对象ID")

    private Long objUserId;

    /** 操作对象名称 */
    @Excel(name = "操作对象名称")

    private String objUserName;

    /** 操作类型 */
    @Excel(name = "操作类型")

    private String type;

    /** 详情 */
    @Excel(name = "详情")

    private String detail;

    /** IP地址 */
    @Excel(name = "IP地址")

    private String ip;

    /** IP所属地 */
    @Excel(name = "IP所属地")

    private String ipCity;

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
    public void setObjUserId(Long objUserId) 
    {
        this.objUserId = objUserId;
    }

    public Long getObjUserId() 
    {
        return objUserId;
    }
    public void setObjUserName(String objUserName) 
    {
        this.objUserName = objUserName;
    }

    public String getObjUserName() 
    {
        return objUserName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setIpCity(String ipCity) 
    {
        this.ipCity = ipCity;
    }

    public String getIpCity() 
    {
        return ipCity;
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
            .append("objUserId", getObjUserId())
            .append("objUserName", getObjUserName())
            .append("type", getType())
            .append("detail", getDetail())
            .append("ip", getIp())
            .append("ipCity", getIpCity())
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
