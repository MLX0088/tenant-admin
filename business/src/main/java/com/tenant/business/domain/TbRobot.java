package com.tenant.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 机器人管理对象 tb_robot
 *
 * @author luanyu
 * @date 2025-05-03
 */
public class TbRobot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 头像id */
    @Excel(name = "头像id")

    private Long avatarId;

    /** 昵称 */
    @Excel(name = "昵称")

    private String name;

    /** 游戏房间 */
    @Excel(name = "游戏房间")

    private String roomName;

    /** 余分 */
    @Excel(name = "余分")

    private BigDecimal leftScore;

    /** 开始执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始执行时间")

    private String starttime;

    /** 结束执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束执行时间")

    private String endtime;

    /** 状态 */
    @Excel(name = "状态")

    private String status;

    /** 上分规则 */
    @Excel(name = "上分规则")

    private Long addScoreRule;

    /** 上分数字 */
    @Excel(name = "上分数字")

    private Long addScore;

    /** 下分规则 */
    @Excel(name = "下分规则")

    private Long minusScoreRule;

    /** 下分数字 */
    @Excel(name = "下分数字")

    private Long minusScore;

    /** 等级 */
    @Excel(name = "等级")

    private Long grade;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;
    private int refCount;
    private int refManualCount;

    private String imageUrl;

    private Date lastBettingTime;

    public Date getLastBettingTime() {
        return lastBettingTime;
    }

    public void setLastBettingTime(Date lastBettingTime) {
        this.lastBettingTime = lastBettingTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getRefManualCount() {
        return refManualCount;
    }

    public void setRefManualCount(int refManualCount) {
        this.refManualCount = refManualCount;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAvatarId(Long avatarId)
    {
        this.avatarId = avatarId;
    }

    public Long getAvatarId()
    {
        return avatarId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public String getRoomName()
    {
        return roomName;
    }
    public void setLeftScore(BigDecimal leftScore)
    {
        this.leftScore = leftScore;
    }

    public BigDecimal getLeftScore()
    {
        return leftScore;
    }
    public void setStarttime(String starttime)
    {
        this.starttime = starttime;
    }

    public String getStarttime()
    {
        return starttime;
    }
    public void setEndtime(String endtime)
    {
        this.endtime = endtime;
    }

    public String getEndtime()
    {
        return endtime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setAddScoreRule(Long addScoreRule)
    {
        this.addScoreRule = addScoreRule;
    }

    public Long getAddScoreRule()
    {
        return addScoreRule;
    }
    public void setAddScore(Long addScore)
    {
        this.addScore = addScore;
    }

    public Long getAddScore()
    {
        return addScore;
    }
    public void setMinusScoreRule(Long minusScoreRule)
    {
        this.minusScoreRule = minusScoreRule;
    }

    public Long getMinusScoreRule()
    {
        return minusScoreRule;
    }
    public void setMinusScore(Long minusScore)
    {
        this.minusScore = minusScore;
    }

    public Long getMinusScore()
    {
        return minusScore;
    }
    public void setGrade(Long grade)
    {
        this.grade = grade;
    }

    public Long getGrade()
    {
        return grade;
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
            .append("avatarId", getAvatarId())
            .append("name", getName())
            .append("roomName", getRoomName())
            .append("leftScore", getLeftScore())
            .append("starttime", getStarttime())
            .append("endtime", getEndtime())
            .append("status", getStatus())
            .append("addScoreRule", getAddScoreRule())
            .append("addScore", getAddScore())
            .append("minusScoreRule", getMinusScoreRule())
            .append("minusScore", getMinusScore())
            .append("grade", getGrade())
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
