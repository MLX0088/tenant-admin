package com.tenant.business.domain.vo;

import com.tenant.business.domain.TbRoomConfig;

import java.util.Date;

public class DrawDashboardVo {
    private String periodNumber;
    private Date expectOpenTime;
    private Date actualOpenTime;
    private Integer resultStatus;
    private Integer resultOne;
    private Integer resultTwo;
    private Integer resultThree;
    private Integer resultSum;
    private String simpleResult;
    private Integer userCount;
    private Integer totalScore;
    private Double totalWinScore;

    public Date getExpectOpenTime() {
        return expectOpenTime;
    }

    public void setExpectOpenTime(Date expectOpenTime) {
        this.expectOpenTime = expectOpenTime;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public Date getActualOpenTime() {
        return actualOpenTime;
    }

    public void setActualOpenTime(Date actualOpenTime) {
        this.actualOpenTime = actualOpenTime;
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

    public String getSimpleResult() {
        return simpleResult;
    }

    public void setSimpleResult(String simpleResult) {
        this.simpleResult = simpleResult;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Double getTotalWinScore() {
        return totalWinScore;
    }

    public void setTotalWinScore(Double totalWinScore) {
        this.totalWinScore = totalWinScore;
    }
}
