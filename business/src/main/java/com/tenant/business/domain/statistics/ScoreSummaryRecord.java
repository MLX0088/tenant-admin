package com.tenant.business.domain.statistics;

public class ScoreSummaryRecord {
    private String userId;
    private String userName;
    private int scorePerson;
    private double totalWin;
    private int totalScore;
    private String room;
    private double fhjl;
    private double bsjl;
    private double lsjl;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScorePerson() {
        return scorePerson;
    }

    public void setScorePerson(int scorePerson) {
        this.scorePerson = scorePerson;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(double totalWin) {
        this.totalWin = totalWin;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getFhjl() {
        return fhjl;
    }

    public void setFhjl(double fhjl) {
        this.fhjl = fhjl;
    }

    public double getBsjl() {
        return bsjl;
    }

    public void setBsjl(double bsjl) {
        this.bsjl = bsjl;
    }

    public double getLsjl() {
        return lsjl;
    }

    public void setLsjl(double lsjl) {
        this.lsjl = lsjl;
    }
}
