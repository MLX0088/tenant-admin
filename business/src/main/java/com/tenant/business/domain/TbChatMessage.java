package com.tenant.business.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 消息对象 tb_chat_message
 * 
 * @author luanyu
 * @date 2025-05-24
 */
public class TbChatMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */

    private Long id;

    /** 发送者类型 0：真人 1：机器人 2服务器推送 */
    @Excel(name = "发送者类型 0：真人 1：机器人 2服务器推送")
    private String senderType;

    /** 发送者ID */
    @Excel(name = "发送者ID")
    private Long senderId;
    private String senderUserName;
    private Long senderHeadImageId;
    private Long senderGrade;
    /** 接收者ID（单聊时使用） */
    @Excel(name = "接收者ID", readConverterExp = "单=聊时使用")
    private Long receiverId;

    /** 群组ID（群聊时使用） */
    @Excel(name = "群组ID", readConverterExp = "群=聊时使用")
    private Long groupId;
    /** 文本内容或图片URL */
    @Excel(name = "文本内容或图片URL")
    private String content;

    private Long imageId;
    /**  */
    @Excel(name = "")
    private int type;
    private Long scoreId;

    public Long getScoreId() {
        return scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date createdTime;

    private Long tenantId;
    private Long drawId;
    private Long roomConfigId;
    private Integer status;
    private Integer readStatus;
    private int success;
    private int rebotMessageType; //1：回复投注结果 2：定时推送 3：投注列表推送 4：开奖结果推送
    private List<Long> privateIds;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public List<Long> getPrivateIds() {
        return privateIds;
    }

    public void setPrivateIds(List<Long> privateIds) {
        this.privateIds = privateIds;
    }

    public int getRebotMessageType() {
        return rebotMessageType;
    }

    public void setRebotMessageType(int rebotMessageType) {
        this.rebotMessageType = rebotMessageType;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public Long getRoomConfigId() {
        return roomConfigId;
    }

    public void setRoomConfigId(Long roomConfigId) {
        this.roomConfigId = roomConfigId;
    }

    public Long getDrawId() {
        return drawId;
    }

    public void setDrawId(Long drawId) {
        this.drawId = drawId;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public Long getSenderHeadImageId() {
        return senderHeadImageId;
    }

    public void setSenderHeadImageId(Long senderHeadImageId) {
        this.senderHeadImageId = senderHeadImageId;
    }

    public Long getSenderGrade() {
        return senderGrade;
    }

    public void setSenderGrade(Long senderGrade) {
        this.senderGrade = senderGrade;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSenderType(String senderType) 
    {
        this.senderType = senderType;
    }

    public String getSenderType() 
    {
        return senderType;
    }
    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }
    public void setReceiverId(Long receiverId) 
    {
        this.receiverId = receiverId;
    }

    public Long getReceiverId() 
    {
        return receiverId;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("senderType", getSenderType())
            .append("senderId", getSenderId())
            .append("receiverId", getReceiverId())
            .append("groupId", getGroupId())
            .append("content", getContent())
            .append("type", getType())
            .append("createdTime", getCreatedTime())
            .toString();
    }
}
