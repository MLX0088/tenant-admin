package com.tenant.common.domain;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 附件对象 attachment
 * 
 * @author luanyu
 * @date 2022-09-19
 */
public class Attachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private String id;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String name;

    /** 新名称 */
    @Excel(name = "新名称")
    private String newName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String type;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String path;
    /**
     * 百度fsId
     */
    private Long fsId;

    /** 文件大小(Bite) */
    @Excel(name = "文件大小(Bite)")
    private Long size;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNewName(String newName) 
    {
        this.newName = newName;
    }

    public String getNewName() 
    {
        return newName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }

    public Long getFsId() {
        return fsId;
    }

    public void setFsId(Long fsId) {
        this.fsId = fsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("newName", getNewName())
            .append("type", getType())
            .append("path", getPath())
            .append("size", getSize())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
