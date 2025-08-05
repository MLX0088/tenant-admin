package com.tenant.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 投注记录对象 tb_score_record
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbScoreRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")

    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")

    private String userName;

    /** 开奖记录id */

    private Long drawId;

    /** 房间 */
    @Excel(name = "房间")

    private String roomName;

    /** 期号 */
    @Excel(name = "期号")

    private String periodNumber;

    /** 开奖结果 */
    @Excel(name = "开奖结果")

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

    /** 相差秒 */
    @Excel(name = "相差秒")

    private Long diffSecond;

    /** 状态 */
    @Excel(name = "状态")

    private String status;

    /** 是否单注（0代表不是 1代表是） */

    private Integer isSingle;

    /** 是否组合（0代表不是 1代表是） */

    private Integer isCombination;

    /** 是否数字（0代表不是 1代表是） */

    private Integer isNumber;

    /** 是否反组合（0代表不是 1代表是） */

    private Integer isAntiCombination;

    /** 是否龙虎豹（0代表不是 1代表是） */

    private Integer isBoldThree;

    /** 是否超无视（0代表不是 1代表是） */

    private Integer isSuperIgnore;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */

    private Long tenantId;

    private Integer resultOne;

    private Integer resultTwo;

    private Integer resultThree;

    private Integer resultSum;

    private Long roomConfigId;

    private String registerIp;
    private String registerAddress;
    private int registerIpRepeat;

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public int getRegisterIpRepeat() {
        return registerIpRepeat;
    }

    public void setRegisterIpRepeat(int registerIpRepeat) {
        this.registerIpRepeat = registerIpRepeat;
    }

    public Long getRoomConfigId() {
        return roomConfigId;
    }

    public void setRoomConfigId(Long roomConfigId) {
        this.roomConfigId = roomConfigId;
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
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

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getIsCombination() {
        return isCombination;
    }

    public void setIsCombination(Integer isCombination) {
        this.isCombination = isCombination;
    }

    public Integer getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(Integer isNumber) {
        this.isNumber = isNumber;
    }

    public Integer getIsAntiCombination() {
        return isAntiCombination;
    }

    public void setIsAntiCombination(Integer isAntiCombination) {
        this.isAntiCombination = isAntiCombination;
    }

    public Integer getIsBoldThree() {
        return isBoldThree;
    }

    public void setIsBoldThree(Integer isBoldThree) {
        this.isBoldThree = isBoldThree;
    }

    public Integer getIsSuperIgnore() {
        return isSuperIgnore;
    }

    public void setIsSuperIgnore(Integer isSuperIgnore) {
        this.isSuperIgnore = isSuperIgnore;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("drawId", getDrawId())
            .append("roomName", getRoomName())
            .append("periodNumber", getPeriodNumber())
            .append("result", getResult())
            .append("betting", getBetting())
            .append("score", getScore())
            .append("winScore", getWinScore())
            .append("leftScore", getLeftScore())
            .append("diffSecond", getDiffSecond())
            .append("status", getStatus())
            .append("isSingle", getIsSingle())
            .append("isCombination", getIsCombination())
            .append("isNumber", getIsNumber())
            .append("isAntiCombination", getIsAntiCombination())
            .append("isBoldThree", getIsBoldThree())
            .append("isSuperIgnore", getIsSuperIgnore())
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
