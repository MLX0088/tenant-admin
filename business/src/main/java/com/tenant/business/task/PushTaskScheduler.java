package com.tenant.business.task;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PushTaskScheduler {
    @Autowired
    private TaskScheduler taskScheduler; // 注入线程池调度器
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ITbChatMessageService tbChatMessageService;


    @PostConstruct
    public void init() {
        Runnable taskPause = () -> {
            fisth();
        };
        Thread thread = new Thread(taskPause);
        thread.start();
    }

    List<TbRoomConfig> configList = null;
    Map<Long,TbTenantConfig> tenantConfigMap = new HashMap<>();
    private void refreshData(){
        configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());
        List<TbTenantConfig> tenantConfigList = tbTenantConfigService.selectTbTenantConfigList(new TbTenantConfig());

        for (TbTenantConfig config : tenantConfigList){
            tenantConfigMap.put(config.getTenantId(),config);
        }
    }

    private void fisth() {

        int count = 0;
        while (true){
            if(count % 600 == 0){ //10分钟刷新一次数据
                refreshData();
            }
            count++;
            long now = new Date().getTime(); // 毫秒差
            long secondsDiffJND = 0l; // 转换为秒（整数部分）
            long secondsDiffAM = 0l; // 转换为秒（整数部分）
            if(Global.jndOpenDate != null){
                secondsDiffJND = (Global.jndOpenDate.getTime() -now) / 1000; // 转换为秒（整数部分）
            }
            if(Global.twOpenDate != null){
                secondsDiffAM = (Global.twOpenDate.getTime() -now) / 1000; // 转换为秒（整数部分）
            }

            for (TbRoomConfig config:configList) {
                if(!"0".equals(config.getIsOpen())){
                    continue;
                }
                long diff = config.getRoomName().contains("加拿大")?secondsDiffJND:secondsDiffAM;
                if(diff < 30){
                    continue;
                }
                if(tenantConfigMap.get(config.getTenantId())==null){
                    continue;
                }
                for (TbRoomPush push: config.getPushList()) {
                    if(push==null || push.getSecond() == null){
                        continue;
                    }
                    if(push.getSecond() == diff-30){

                        TbChatMessage messageRebot = new TbChatMessage();
                        messageRebot.setSenderId(-1l);
                        messageRebot.setCreatedTime(new Date());
                        messageRebot.setSenderType("1");
                        messageRebot.setTenantId(config.getTenantId());
                        messageRebot.setSenderHeadImageId(tenantConfigMap.get(config.getTenantId()).getRebotImageId());
                        messageRebot.setSenderUserName(tenantConfigMap.get(config.getTenantId()).getRebotName());
                        messageRebot.setGroupId(config.getId());
                        messageRebot.setContent(push.getContent());
                        messageRebot.setRebotMessageType(2);
                        tbChatMessageService.insertTbChatMessage(messageRebot);
                        messagingTemplate.convertAndSend(
                                "/topic/group/" + messageRebot.getGroupId(),
                                messageRebot
                        );
                        break;
                    }
                }
            }
            try {
                long last = new Date().getTime(); // 毫秒差
                if(1000 > last-now){
                    Thread.sleep(1000-(last-now));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
