package com.tenant.business.domain.statistics;

public class ChannelRecord {
    private long id;
    private long userId;
    private String userName;
    private String name;
    private String remark;
    private String createTime;
    private int addPeople;
    private int totalAddPeople;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getAddPeople() {
        return addPeople;
    }

    public void setAddPeople(int addPeople) {
        this.addPeople = addPeople;
    }

    public int getTotalAddPeople() {
        return totalAddPeople;
    }

    public void setTotalAddPeople(int totalAddPeople) {
        this.totalAddPeople = totalAddPeople;
    }
}
