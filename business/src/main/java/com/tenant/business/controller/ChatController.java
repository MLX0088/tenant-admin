package com.tenant.business.controller;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.annotation.Log;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.core.page.TableDataInfo;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.utils.AddressUtils;
import com.tenant.common.utils.ServletUtils;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 消息Controller
 * 
 * @author luanyu
 * @date 2025-05-24
 */
@Controller
@RequestMapping("/business/ChatMessage")
public class ChatController extends BaseController
{
    private String prefix = "business/ChatMessage";

    @Autowired
    private ITbChatMessageService tbChatMessageService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;
    @Autowired
    private ISysUserService sysUserService;

    public class HeartbeatResponse {
        private String timestamp;
        private String responseType;

        public HeartbeatResponse() {}

        public HeartbeatResponse(LocalDateTime timestamp, String responseType) {
            this.timestamp = timestamp.format(DateTimeFormatter.ISO_DATE_TIME);
            this.responseType = responseType;
        }

        // Getter 和 Setter
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getResponseType() {
            return responseType;
        }

        public void setResponseType(String responseType) {
            this.responseType = responseType;
        }
    }
    @MessageMapping("/heartbeat")
    @SendToUser("/queue/heartbeat")
    public HeartbeatResponse handleHeartbeat() {
        return new HeartbeatResponse(LocalDateTime.now(), "PONG");
    }
    // 单聊消息发送
    @MessageMapping("/chat/private")
    public void sendPrivateMessage(@Payload TbChatMessage message) {
        if(message.getSenderId()!=null && message.getSenderId()>0){
            SysUser user = sysUserService.selectUserById(message.getSenderId());
            message.setSenderHeadImageId(user.getHeadImageId());
            message.setSenderUserName(user.getUserName());
            message.setSenderGrade(user.getGrade());
            message.setTenantId(user.getDeptId());
        }else{
            SysUser user = sysUserService.selectUserById(message.getReceiverId());
            TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(user.getDeptId());
            message.setTenantId(user.getDeptId());
            message.setSenderHeadImageId(config.getRebotImageId());
            message.setSenderUserName(config.getCsName());
        }
        message.setCreatedTime(new Date());
        message.setSenderType("0");
        message.setReadStatus(0);
        tbChatMessageService.insertTbChatMessage(message);

        // 消息存储逻辑（需自行实现）
        messagingTemplate.convertAndSend(
                "/user/"+message.getSenderId()+"/queue/private",
                message
        );

        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 消息存储逻辑（需自行实现）
        messagingTemplate.convertAndSend(
                "/user/"+message.getReceiverId()+"/queue/private",
                message
        );
    }

    // 群聊消息发送
    @MessageMapping("/chat/group")
    @Transactional
    public synchronized void sendGroupMessage(@Payload TbChatMessage message, SimpMessageHeaderAccessor accessor) {


        String ip = accessor.getSessionAttributes().get("ip")==null?"":accessor.getSessionAttributes().get("ip").toString();
        SysUser user = sysUserService.selectUserById(message.getSenderId());
        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(user.getDeptId());

        if(Global.isBanBettingModel ){
            TbChatMessage messageRebot = new TbChatMessage();
            messageRebot.setSenderId(-1l);
            messageRebot.setGroupId(message.getGroupId());
            messageRebot.setCreatedTime(new Date());
            messageRebot.setSenderHeadImageId(config.getRebotImageId());
            messageRebot.setSenderUserName(config.getRebotName());
            messageRebot.setSenderType("1");
            messageRebot.setTenantId(user.getDeptId());
            messageRebot.setDrawId(message.getDrawId());
            messageRebot.setRebotMessageType(1);
            messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>当前是禁投模式");
            tbChatMessageService.insertTbChatMessage(messageRebot);
            messagingTemplate.convertAndSend(
                    "/topic/group/" + message.getGroupId(),
                    messageRebot
            );
            return;

        }

        TbDrawRecord drawRecord = tbDrawRecordService.selectTbDrawRecordById(message.getDrawId());
        if(drawRecord == null){
            return;
        }
        TbRoomConfig roomConfig = tbRoomConfigService.selectTbRoomConfigById(message.getRoomConfigId());

        if(roomConfig == null){
            return;
        }

        long time = new Date().getTime();
        if(roomConfig.getRoomName().contains("加拿大") ){
            if(Global.jndCurrentDraw == null || Global.jndIsMaintain){
                TbChatMessage messageRebot = new TbChatMessage();
                messageRebot.setSenderId(-1l);
                messageRebot.setGroupId(message.getGroupId());
                messageRebot.setCreatedTime(new Date());
                messageRebot.setSenderHeadImageId(config.getRebotImageId());
                messageRebot.setSenderUserName(config.getRebotName());
                messageRebot.setSenderType("1");
                messageRebot.setTenantId(user.getDeptId());
                messageRebot.setDrawId(message.getDrawId());
                messageRebot.setRebotMessageType(1);
                messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>维护中");
                tbChatMessageService.insertTbChatMessage(messageRebot);
                messagingTemplate.convertAndSend(
                        "/topic/group/" + message.getGroupId(),
                        messageRebot
                );
                return;

            }else if (Global.jndCurrentDraw.getResultStatus() == null || Global.jndCurrentDraw.getResultStatus()>0 || Global.jndCurrentDraw.getExpectOpenTime().getTime() <= (time+1000*31) ){
                TbChatMessage messageRebot = new TbChatMessage();
                messageRebot.setSenderId(-1l);
                messageRebot.setGroupId(message.getGroupId());
                messageRebot.setCreatedTime(new Date());
                messageRebot.setSenderHeadImageId(config.getRebotImageId());
                messageRebot.setSenderUserName(config.getRebotName());
                messageRebot.setSenderType("1");
                messageRebot.setTenantId(user.getDeptId());
                messageRebot.setDrawId(message.getDrawId());
                messageRebot.setRebotMessageType(1);
                messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>已封盘");
                tbChatMessageService.insertTbChatMessage(messageRebot);
                messagingTemplate.convertAndSend(
                        "/topic/group/" + message.getGroupId(),
                        messageRebot
                );
                return;
            }
        }else{
            if(Global.twCurrentDraw == null || Global.twIsMaintain){
                TbChatMessage messageRebot = new TbChatMessage();
                messageRebot.setSenderId(-1l);
                messageRebot.setGroupId(message.getGroupId());
                messageRebot.setCreatedTime(new Date());
                messageRebot.setSenderHeadImageId(config.getRebotImageId());
                messageRebot.setSenderUserName(config.getRebotName());
                messageRebot.setSenderType("1");
                messageRebot.setTenantId(user.getDeptId());
                messageRebot.setDrawId(message.getDrawId());
                messageRebot.setRebotMessageType(1);
                messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>维护中");
                tbChatMessageService.insertTbChatMessage(messageRebot);
                messagingTemplate.convertAndSend(
                        "/topic/group/" + message.getGroupId(),
                        messageRebot
                );
                return;

            }else if (Global.twCurrentDraw.getResultStatus() == null || Global.twCurrentDraw.getResultStatus()>0 || Global.twCurrentDraw.getExpectOpenTime().getTime() <= (time+1000*31)){
                TbChatMessage messageRebot = new TbChatMessage();
                messageRebot.setSenderId(-1l);
                messageRebot.setGroupId(message.getGroupId());
                messageRebot.setCreatedTime(new Date());
                messageRebot.setSenderHeadImageId(config.getRebotImageId());
                messageRebot.setSenderUserName(config.getRebotName());
                messageRebot.setSenderType("1");
                messageRebot.setTenantId(user.getDeptId());
                messageRebot.setDrawId(message.getDrawId());
                messageRebot.setRebotMessageType(1);
                messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>已封盘");
                tbChatMessageService.insertTbChatMessage(messageRebot);
                messagingTemplate.convertAndSend(
                        "/topic/group/" + message.getGroupId(),
                        messageRebot
                );
                return;
            }
        }
        TbChatMessage messageRebot = new TbChatMessage();
        messageRebot.setSenderId(-1l);
        messageRebot.setGroupId(message.getGroupId());
        messageRebot.setCreatedTime(new Date());
        messageRebot.setSenderHeadImageId(config.getRebotImageId());
        messageRebot.setSenderUserName(config.getRebotName());
        messageRebot.setSenderType("1");
        messageRebot.setTenantId(user.getDeptId());
        messageRebot.setDrawId(message.getDrawId());
        messageRebot.setRebotMessageType(1);
        TbScoreRecord record = new TbScoreRecord();
        record.setBetting(message.getContent().trim());
        String vaildResult = DrawUtils.validBettingScore(message.getContent().trim(),user.getScore().doubleValue(),roomConfig);
        if(vaildResult == null){
            double sumScore = DrawUtils.parseBetting(record);
            user.setScore(user.getScore().subtract(new BigDecimal(sumScore)));

            SysUser tempUser = new SysUser(user.getUserId());
            tempUser.setScore(user.getScore());
            tempUser.setLastScoreTime(new Date());
            sysUserService.updateUserInfo(tempUser);

            long diff = Math.round((drawRecord.getExpectOpenTime().getTime() - (new Date().getTime()))/1000);
            record.setTenantId(user.getDeptId());
            record.setDrawId(message.getDrawId());
            record.setPeriodNumber(drawRecord.getPeriodNumber());
            record.setLeftScore(user.getScore());
            record.setUserId(user.getUserId());
            record.setUserName(user.getUserName());
            record.setScore(new BigDecimal(sumScore));
            record.setRoomName(roomConfig.getRoomName());
            record.setRoomConfigId(roomConfig.getId());
            record.setDiffSecond(diff);

            record.setRegisterIp(ip);

            if(tbScoreRecordService.selectByIp(record) != null){
                record.setRegisterIpRepeat(1);
            }
            record.setRegisterAddress(AddressUtils.getRealAddressByIP(ip));
            tbScoreRecordService.insertTbScoreRecord(record);

            message.setSuccess(1);
            messageRebot.setContent("@"+user.getUserName()+" 下注成功<br>"+message.getContent()+" 余额："+user.getScore().doubleValue());
        }else{
            message.setSuccess(2);
            messageRebot.setContent("@"+user.getUserName()+" 下注失败<br>"+vaildResult);
        }
        message.setSenderHeadImageId(user.getHeadImageId());
        message.setSenderUserName(user.getUserName());
        message.setSenderGrade(user.getGrade());
        message.setTenantId(user.getDeptId());
        message.setCreatedTime(new Date());
        message.setSenderType("0");
        message.setScoreId(record.getId());
        tbChatMessageService.insertTbChatMessage(message);
        tbChatMessageService.insertTbChatMessage(messageRebot);


        // 消息存储逻辑（需自行实现）
        messagingTemplate.convertAndSend(
                "/topic/group/" + message.getGroupId(),
                message
        );

        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        messagingTemplate.convertAndSend(
                "/topic/group/" + message.getGroupId(),
                messageRebot
        );
    }
}
