package com.tenant.business.domain.statistics;

public class PersonWinRecord {
    private String date;
    private int addScore;
    private int minusScore;
    private double otherScore;
    private int totalScore;
    private double totalWin;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAddScore() {
        return addScore;
    }

    public void setAddScore(int addScore) {
        this.addScore = addScore;
    }

    public int getMinusScore() {
        return minusScore;
    }

    public void setMinusScore(int minusScore) {
        this.minusScore = minusScore;
    }

    public double getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(double otherScore) {
        this.otherScore = otherScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(double totalWin) {
        this.totalWin = totalWin;
    }
}
