package com.tenant.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 机器人投注记录对象 tb_rebot_score_record
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbRebotScoreRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 机器人ID */

    private Long robotId;

    /** 开奖记录id */

    private Long drawId;

    /** 房间 */
    @Excel(name = "房间")

    private String roomName;

    /** 期号 */
    @Excel(name = "期号")

    private String periodNumber;

    /** 开奖结果 */

    private String result;

    /** 投码 */
    @Excel(name = "投码")

    private String betting;

    /** 总投分 */
    @Excel(name = "总投分")

    private BigDecimal score;

    /** 盈亏分 */
    @Excel(name = "盈亏分")

    private BigDecimal winScore;

    /** 当前余分 */
    @Excel(name = "当前余分")

    private BigDecimal leftScore;

    /** 状态 */
    @Excel(name = "状态")

    private Long status;

    /** 类型 */
    @Excel(name = "类型")

    private Long type;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;

    private Long roomConfigId;
    private String robotName;

    public TbScoreRecord parseTo(){
        TbScoreRecord score = new TbScoreRecord();
        score.setWinScore(this.winScore);
        score.setStatus(this.status+"");
        score.setCreateTime(this.getCreateTime());
        score.setUserName(this.robotName);
        score.setLeftScore(this.leftScore);
        score.setBetting(this.betting);
        return score;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Long getRoomConfigId() {
        return roomConfigId;
    }

    public void setRoomConfigId(Long roomConfigId) {
        this.roomConfigId = roomConfigId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRobotId(Long robotId) 
    {
        this.robotId = robotId;
    }

    public Long getRobotId() 
    {
        return robotId;
    }
    public void setDrawId(Long drawId) 
    {
        this.drawId = drawId;
    }

    public Long getDrawId() 
    {
        return drawId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setPeriodNumber(String periodNumber) 
    {
        this.periodNumber = periodNumber;
    }

    public String getPeriodNumber() 
    {
        return periodNumber;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setBetting(String betting) 
    {
        this.betting = betting;
    }

    public String getBetting() 
    {
        return betting;
    }
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }
    public void setWinScore(BigDecimal winScore) 
    {
        this.winScore = winScore;
    }

    public BigDecimal getWinScore() 
    {
        return winScore;
    }
    public void setLeftScore(BigDecimal leftScore) 
    {
        this.leftScore = leftScore;
    }

    public BigDecimal getLeftScore() 
    {
        return leftScore;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("robotId", getRobotId())
            .append("drawId", getDrawId())
            .append("roomName", getRoomName())
            .append("periodNumber", getPeriodNumber())
            .append("result", getResult())
            .append("betting", getBetting())
            .append("score", getScore())
            .append("winScore", getWinScore())
            .append("leftScore", getLeftScore())
            .append("status", getStatus())
            .append("type", getType())
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
