package com.tenant.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 开奖记录对象 tb_draw_record
 * 
 * @author luanyu
 * @date 2025-05-04
 */
public class TbDrawRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间玩法ID */

    private Long roomConfigId;

    /** 房间 */

    private String roomName;

    /** 期号 */
    @Excel(name = "期号")

    private String periodNumber;

    /** 结果 */
    @Excel(name = "结果")

    private String result;

    private String simpleResult;

    /** 理论开盘时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "理论开盘时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date expectOpenTime;

    /** 实际开盘时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实际开盘时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date actualOpenTime;

    private Date nextOpenTime;
    private String nextPeriodNumber;

    /** 差秒 */
    @Excel(name = "差秒")

    private Long diffSecond;

    /** 状态(0自动开奖 1手动开奖) */

    private String status;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;

    /** 0加拿大 1澳门 2台湾 */

    private Integer gameType;

    private Integer resultOne;

    private Integer resultTwo;

    private Integer resultThree;

    private Integer resultSum;

    private Integer resultStatus;

    public String getNextPeriodNumber() {
        return nextPeriodNumber;
    }

    public void setNextPeriodNumber(String nextPeriodNumber) {
        this.nextPeriodNumber = nextPeriodNumber;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Date getNextOpenTime() {
        return nextOpenTime;
    }

    public void setNextOpenTime(Date nextOpenTime) {
        this.nextOpenTime = nextOpenTime;
    }

    public Integer getResultOne() {
        return resultOne;
    }

    public void setResultOne(Integer resultOne) {
        this.resultOne = resultOne;
    }

    public Integer getResultTwo() {
        return resultTwo;
    }

    public void setResultTwo(Integer resultTwo) {
        this.resultTwo = resultTwo;
    }

    public Integer getResultThree() {
        return resultThree;
    }

    public void setResultThree(Integer resultThree) {
        this.resultThree = resultThree;
    }

    public Integer getResultSum() {
        return resultSum;
    }

    public void setResultSum(Integer resultSum) {
        this.resultSum = resultSum;
    }
    private Set<String> drawSet = new HashSet<>();

    public Set<String> getDrawSet() {
        return drawSet;
    }

    public void setDrawSet(Set<String> drawSet) {
        this.drawSet = drawSet;
    }

    public String getSimpleResult() {
        return simpleResult;
    }

    public void setSimpleResult(String simpleResult) {
        this.simpleResult = simpleResult;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomConfigId(Long roomConfigId) 
    {
        this.roomConfigId = roomConfigId;
    }

    public Long getRoomConfigId() 
    {
        return roomConfigId;
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
        if(result != null && result.length()>0){
            String[] draws = result.split(" ");
            for (String str:draws) {
                drawSet.add(str);
            }
        }
    }

    public String getResult() 
    {
        return result;
    }
    public void setExpectOpenTime(Date expectOpenTime) 
    {
        this.expectOpenTime = expectOpenTime;
    }

    public Date getExpectOpenTime() 
    {
        return expectOpenTime;
    }
    public void setActualOpenTime(Date actualOpenTime) 
    {
        this.actualOpenTime = actualOpenTime;
    }

    public Date getActualOpenTime() 
    {
        return actualOpenTime;
    }
    public void setDiffSecond(Long diffSecond) 
    {
        this.diffSecond = diffSecond;
    }

    public Long getDiffSecond() 
    {
        return diffSecond;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
    public void setGameType(Integer gameType) 
    {
        this.gameType = gameType;
    }

    public Integer getGameType() 
    {
        return gameType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomConfigId", getRoomConfigId())
            .append("roomName", getRoomName())
            .append("periodNumber", getPeriodNumber())
            .append("result", getResult())
            .append("expectOpenTime", getExpectOpenTime())
            .append("actualOpenTime", getActualOpenTime())
            .append("diffSecond", getDiffSecond())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("gameType", getGameType())
            .toString();
    }
}
