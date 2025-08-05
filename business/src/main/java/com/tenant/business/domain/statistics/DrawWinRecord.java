package com.tenant.business.domain.statistics;

public class DrawWinRecord {
    private String periodNumber;
    private String expectOpenTime;
    private String result;
    private double winScore;

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getExpectOpenTime() {
        return expectOpenTime;
    }

    public void setExpectOpenTime(String expectOpenTime) {
        this.expectOpenTime = expectOpenTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getWinScore() {
        return winScore;
    }

    public void setWinScore(double winScore) {
        this.winScore = winScore;
    }
}
