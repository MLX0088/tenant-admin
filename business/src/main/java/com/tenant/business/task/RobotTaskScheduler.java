package com.tenant.business.task;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.domain.SysConfig;
import com.tenant.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
public class RobotTaskScheduler {
    @Autowired
    private TaskScheduler taskScheduler; // 注入线程池调度器
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ITbChatMessageService tbChatMessageService;
    @Autowired
    private ITbRebotScoreRecordService tbRebotScoreRecordService;
    @Autowired
    private ITbRobotService tbRobotService;
    @Autowired
    private ISysConfigService sysConfigService;


    @PostConstruct
    public void init() {
        Runnable taskPause = () -> {
            fisth();
        };
        Thread thread = new Thread(taskPause);
        thread.start();
    }

    List<TbTenantConfig> tenantConfigList = null;
    Map<Long,TbTenantConfig> tenantConfigMap = new HashMap<>();
    Map<Long,Long> roomBettingTimeMap = new HashMap<>();
    Map<Long,List<TbRobot>> roomRobotsMap = new HashMap<>();
    Map<String,TbRoomConfig> roomMap = new HashMap<>();
    Map<Long,Set<Long>> roomRobotUsedSet = new HashMap<>();

    private void refreshData(){
        try{
            tenantConfigList = tbTenantConfigService.selectTbTenantConfigList(new TbTenantConfig());
            for (TbTenantConfig config : tenantConfigList){
                if(config.getTenantId()==-1){
                    continue;
                }
                tenantConfigMap.put(config.getTenantId(),config);
            }
            List<TbRoomConfig> configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());

            for (TbRoomConfig config : configList){
                if(config.getTenantId()==-1){
                    continue;
                }
                roomBettingTimeMap.put(config.getId(),0l);
                roomMap.put(config.getTenantId()+"-"+config.getRoomName(),config);
                roomRobotsMap.put(config.getId(),new ArrayList<>());
                roomRobotUsedSet.put(config.getId(),new HashSet<>());
            }

            TbRobot paramRobot = new TbRobot();
            paramRobot.setStatus("0");
            List<TbRobot> robotList = tbRobotService.selectTbRobotList(paramRobot);
            for(TbRobot robot : robotList){
                if(robot.getTenantId()==-1){
                    continue;
                }
                TbRoomConfig config = roomMap.get(robot.getTenantId()+"-"+robot.getRoomName());
                if(config != null && roomRobotsMap.containsKey(config.getId())){
                    roomRobotsMap.get(config.getId()).add(robot);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void fisth() {

        int baseMin = 100;
        int baseMax = 150;
        int[] comboCount = new int[]{1,2};
        String[] rule = new String[]{"大-小","单-双","尾小-尾大","尾单-尾双","极小-极大","尾1-尾2-尾3-尾4-尾5-尾6-尾7-尾8","点1-点2-点3-点4-点5-点6-点7-点8-点9-点10-点11-点12-点15-点16-点17-点18-点19-点20-点21-点22-点23","对子-顺子","龙-虎-豹"};

        int count = 0;
        String jndNo = "";
        String twNo = "";
        while (true){
            if(count % 600 == 0){ //10分钟刷新一次数据
                refreshData();
            }
            count++;
            long now = new Date().getTime();
            List<TbChatMessage> messageList = new ArrayList<>();
            List<TbChatMessage> robotMessageList = new ArrayList<>();

            if(Global.isBanBettingModel){
                try {
                    Thread.sleep(1000l);
                    continue;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (String str : roomMap.keySet()) {
                try{
                    Set<Long> usedSet = null;
                    TbRoomConfig config = roomMap.get(str);
                    if("1".equals(config.getIsOpen())){
                        continue;
                    }

                    String periodNumber = "";
                    long drawaId = 0L;
                    if(config.getRoomName().contains("加拿大")){
                        if(Global.jndIsMaintain){
                            continue;
                        }
                        if(Global.jndCurrentDraw == null || Global.jndCurrentDraw.getId() == null || Global.jndCurrentDraw.getId().longValue() == 0l || Global.jndCurrentDraw.getResultStatus().intValue() != 0  ){
                            continue;
                        }
                        long openTime = Global.jndCurrentDraw.getExpectOpenTime().getTime();
                        if(now+(1000*40) > openTime){
                            continue;
                        }
                        drawaId = Global.jndCurrentDraw.getId();
                        periodNumber = Global.jndCurrentDraw.getPeriodNumber();

                        if(!periodNumber.equals(jndNo)){
                            roomRobotUsedSet.put(0l,new HashSet<Long>())  ;
                            jndNo = periodNumber;
                        }
                        usedSet = roomRobotUsedSet.get(0l);
                    }else{
                        if(Global.twIsMaintain){
                            continue;
                        }
                        if(Global.twCurrentDraw == null || Global.twCurrentDraw.getId() == null || Global.twCurrentDraw.getId().longValue() == 0l || Global.twCurrentDraw.getResultStatus().intValue() != 0  ){
                            continue;
                        }
                        long openTime = Global.twCurrentDraw.getExpectOpenTime().getTime();
                        if(now+(1000*40) > openTime){
                            continue;
                        }
                        drawaId = Global.twCurrentDraw.getId();
                        periodNumber = Global.twCurrentDraw.getPeriodNumber();

                        if(!periodNumber.equals(twNo)){
                            roomRobotUsedSet.put(2l,new HashSet<Long>())  ;
                            twNo = periodNumber;
                        }
                        usedSet = roomRobotUsedSet.get(2l);
                    }

                    if(now < roomBettingTimeMap.get(config.getId())){
                        continue;
                    }

                    String gap= sysConfigService.selectConfigByKey("rebot.gap."+config.getTenantId());
                    int gapHour = Global.robotAddScoreGap;
                    if(!StringUtils.isEmpty(gap)){
                        gapHour = Integer.parseInt(gap);
                    }

                    TbRobot tbRobot = null;
                    Random rand = new Random();
                    int loopCount = 0;
                    while (tbRobot == null){
                        if(loopCount > roomRobotsMap.get(config.getId()).size()){
                            break;
                        }
                        if(roomRobotsMap.get(config.getId()).size()==0){
                            break;
                        }
                        int randomRobot = rand.nextInt(roomRobotsMap.get(config.getId()).size());
                        if(!usedSet.contains(roomRobotsMap.get(config.getId()).get(randomRobot).getId())){
                            tbRobot =  roomRobotsMap.get(config.getId()).get(randomRobot);
                            if(tbRobot.getLeftScore().doubleValue()>0 || tbRobot.getLastBettingTime()==null || (tbRobot.getLeftScore().longValue()<=tbRobot.getAddScoreRule().longValue() && tbRobot.getLastBettingTime().getTime() + 1000*60*60*gapHour <new Date().getTime())){
                                adjustScore(tbRobot);
                                if(config.getRoomName().contains("加拿大")){
                                    roomRobotUsedSet.get(0l).add(tbRobot.getId());
                                } else {
                                    roomRobotUsedSet.get(2l).add(tbRobot.getId());
                                }

                            }else{
                                tbRobot = null;
                            }
                        }
                        loopCount++;
                    }
                    Date date = new Date();
                    if(tbRobot == null){
                        continue;
                    }

                    int totalScore = 0;
                    int firstIndex = 0;

                    String totalBetting = "";
                    int randomIndex1 = rand.nextInt(comboCount.length);
                    for (int i = 0; i<comboCount[randomIndex1];i++){
                        int randomIndex2 = rand.nextInt(rule.length);
                        if(i == 0){
                            firstIndex = randomIndex2;
                        }else if(i == 1){
                            if(firstIndex == randomIndex2){
                                randomIndex2 = firstIndex == rule.length-1 ? 0 : firstIndex+1;
                            }
                        }
                        String[] rules = rule[randomIndex2].split("-");
                        int randomIndex3 = rand.nextInt(rules.length);
                        String betting = rules[randomIndex3];

                        int minScore = baseMin * tbRobot.getGrade().intValue() * (int)(((tbRobot.getGrade()-1)*0.3)+1);
                        int maxScore = baseMax * tbRobot.getGrade().intValue() * (int)(((tbRobot.getGrade()-1)*0.3)+1);
                        int randomScore = (int)(Math.random() * (maxScore - minScore + 1)) + minScore;
                        if(comboCount[randomIndex1]>1){
                            randomScore *= 0.6;
                        }
                        randomScore = adjustNumber(randomScore);

                        if(totalScore + randomScore >= tbRobot.getLeftScore().doubleValue()){
                            randomScore = (int) (tbRobot.getLeftScore().doubleValue() - totalScore);
                            totalScore += randomScore;
                            if(i > 0){
                                totalBetting += " ";
                            }
                            totalBetting += randomScore+""+betting;
                            break;
                        }
                        totalScore += randomScore;
                        if(i > 0){
                            totalBetting += " ";
                        }
                        totalBetting += randomScore+""+betting;
                    }

                    tbRobot.setLeftScore(tbRobot.getLeftScore().subtract(new BigDecimal(totalScore)));
                    tbRobot.setLastBettingTime(date);

                    if(tbRobot.getLeftScore().intValue()<=0){
                        roomRobotsMap.get(config.getId()).remove(tbRobot);
                    }

                    TbRebotScoreRecord record = new TbRebotScoreRecord();
                    record.setTenantId(tbRobot.getTenantId());
                    record.setBetting(totalBetting);
                    record.setDrawId(drawaId);
                    record.setPeriodNumber(periodNumber);
                    record.setLeftScore(tbRobot.getLeftScore());
                    record.setRobotId(tbRobot.getId());
                    record.setRobotName(tbRobot.getName());
                    record.setScore(new BigDecimal(totalScore));
                    record.setRoomName(config.getRoomName());
                    record.setRoomConfigId(config.getId());
                    tbRebotScoreRecordService.insertTbRebotScoreRecord(record);

                    TbChatMessage message = new TbChatMessage();
                    message.setSuccess(1);
                    message.setSenderHeadImageId(tbRobot.getAvatarId());
                    message.setSenderUserName(tbRobot.getName());
                    message.setSenderGrade(tbRobot.getGrade());
                    message.setTenantId(tbRobot.getTenantId());
                    message.setCreatedTime(new Date());
                    message.setSenderType("0");
                    message.setSenderId(tbRobot.getId());
                    message.setContent(totalBetting);
                    message.setGroupId(config.getId());
                    message.setDrawId(drawaId);
                    message.setRoomConfigId(config.getId());
                    message.setType(0);
                    message.setScoreId(record.getId());
                    TbTenantConfig tenantConfig = tenantConfigMap.get(config.getTenantId());

                    TbChatMessage messageRebot = new TbChatMessage();
                    messageRebot.setSenderId(-1l);
                    messageRebot.setGroupId(message.getGroupId());
                    messageRebot.setCreatedTime(new Date());
                    messageRebot.setSenderHeadImageId(tenantConfig.getRebotImageId());
                    messageRebot.setSenderUserName(tenantConfig.getRebotName());
                    messageRebot.setSenderType("1");
                    messageRebot.setTenantId(config.getTenantId());
                    messageRebot.setDrawId(message.getDrawId());
                    messageRebot.setRebotMessageType(1);

                    TbRobot tempRobot = new TbRobot();
                    tempRobot.setId(tbRobot.getId());
                    tempRobot.setLeftScore(tbRobot.getLeftScore());
                    tempRobot.setLastBettingTime(date);
                    tbRobotService.updateTbRobot(tempRobot);

                    messageRebot.setContent("@"+tbRobot.getName()+" 下注成功<br>"+message.getContent()+" 余额："+tempRobot.getLeftScore().doubleValue());

                    robotMessageList.add(messageRebot);
                    tbChatMessageService.insertTbChatMessage(message);

                    // 消息存储逻辑（需自行实现）
                    messagingTemplate.convertAndSend(
                            "/topic/group/" + message.getGroupId(),
                            message
                    );

                    int randomNum = (int)(Math.random() * (2000 + 1)) + 2000;
                    roomBettingTimeMap.put(config.getId(),now+(randomNum));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (TbChatMessage message:robotMessageList) {

                tbChatMessageService.insertTbChatMessage(message);
                messagingTemplate.convertAndSend(
                        "/topic/group/" + message.getGroupId(),
                        message
                );
            }
            robotMessageList.clear();

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

    public static void adjustScore(TbRobot tbRobot){
        if(tbRobot.getLeftScore().doubleValue() <= tbRobot.getAddScoreRule()){
            tbRobot.setLeftScore(tbRobot.getLeftScore().add(new BigDecimal(tbRobot.getAddScore())));
        }else if(tbRobot.getLeftScore().doubleValue() > tbRobot.getMinusScoreRule()){
            tbRobot.setLeftScore(tbRobot.getLeftScore().subtract(new BigDecimal(tbRobot.getMinusScore())));
        }
    }
    public static int adjustNumber(int num) {
        if (num < 500) {
            return num; // 小于500直接返回原值
        } else if (num < 1000) {
            return (num / 10) * 10; // 500-1000：个位归零[4](@ref)
        } else {
            return (num / 100) * 100; // ≥1000：个位和十位归零[4](@ref)
        }
    }
}
