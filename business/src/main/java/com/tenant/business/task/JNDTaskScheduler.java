package com.tenant.business.task;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawClientUtils;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class JNDTaskScheduler {
    @Autowired
    private TaskScheduler taskScheduler; // 注入线程池调度器
    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;
    @Autowired
    private ITbRebotScoreRecordService tbRebotScoreRecordService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ITbChatMessageService tbChatMessageService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITbRobotService tbRobotService;


    @PostConstruct
    public void init() {

        Runnable taskPause = () -> {
            first();
        };
        Thread thread = new Thread(taskPause);
        thread.start();
    }

    private void scheduleNextExecution() {
        Date nextRunTime = new Date(System.currentTimeMillis()+(310*1000));
        TbDrawRecord draw = performDrawLogic();
        if(draw!=null && draw.getNextOpenTime()!=null){
            nextRunTime = draw.getNextOpenTime();
        }
        Global.jndOpenDate = nextRunTime;

        long now = new Date().getTime();
        if(now < nextRunTime.getTime()-(30*1000)){
            Date pauseTime = new Date(nextRunTime.getTime()-(30*1000));
            // 定义任务执行逻辑
            Runnable taskPause = () -> {
                System.out.println("封盘任务执行中，当前时间: " + new Date());
                performPauseLogic();     // 递归调度下一次执行
            };

            // 提交任务到线程池
            taskScheduler.schedule(taskPause, pauseTime);
        }


        // 定义任务执行逻辑
        Runnable task = () -> {
            System.out.println("开奖任务执行中，当前时间: " + new Date());
            scheduleNextExecution();     // 递归调度下一次执行
        };

        if(nextRunTime.getTime() > now){
            taskScheduler.schedule(task, nextRunTime);
        }else{
            taskScheduler.schedule(task, new Date(now+1000));
        }
    }

    @Transactional
    private void first() {

        System.out.println("加拿大线程启动："+new Date().toString());
        TbDrawRecord lastRecord = tbDrawRecordService.selectLastTbDrawRecordByGameType(0);
        if(lastRecord != null && lastRecord.getResultStatus()!=null && lastRecord.getResultStatus()!=3){
            TbDrawRecord record = null;
            int count = 0;
            while (record == null){
                if(count>3){
                    break;
                }
                count++;
                record = DrawClientUtils.fetchJnd(lastRecord.getPeriodNumber());
                if (record==null){
                    continue;
                }
                lastRecord.setResult(record.getResult());
                lastRecord.setResultOne(record.getResultOne());
                lastRecord.setResultTwo(record.getResultTwo());
                lastRecord.setResultThree(record.getResultThree());
                lastRecord.setResultSum(record.getResultSum());
                lastRecord.setSimpleResult(record.getSimpleResult());
                lastRecord.setResultStatus(3);
                lastRecord.setActualOpenTime(new Date());
                lastRecord.setStatus("0");
                lastRecord.setDiffSecond((lastRecord.getActualOpenTime().getTime()-lastRecord.getExpectOpenTime().getTime())/1000);
                tbDrawRecordService.updateTbDrawRecord(lastRecord);

                List<TbRoomConfig> configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());
                Map<Long,TbRoomConfig> roomConfigMap = new HashMap<>();
                for (TbRoomConfig config:configList) {
                    roomConfigMap.put(config.getId(),config);
                }

                //计算投注结果

                TbScoreRecord paramScore = new TbScoreRecord();
                paramScore.setDrawId(lastRecord.getId());
                paramScore.setStatus("0");
                List<TbScoreRecord> scoreList=tbScoreRecordService.selectTbScoreRecordList(paramScore);
                for (TbScoreRecord score : scoreList ) {
                    if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                        continue;
                    }
                    DrawUtils.calWin(lastRecord,score,roomConfigMap.get(score.getRoomConfigId()));
                    tbScoreRecordService.updateTbScoreRecord(score);

                    double diff = score.getScore().add(score.getWinScore()).doubleValue();
                    if(diff>0){
                        SysUser user = sysUserService.selectUserById(score.getUserId());
                        SysUser updateUser = new SysUser(user.getUserId());
                        updateUser.setScore(user.getScore().add(new BigDecimal(diff)));
                        sysUserService.updateUserInfo(updateUser);
                    }

                }

                //计算机器人投注结果
                TbRebotScoreRecord paramRebotScore = new TbRebotScoreRecord();
                paramRebotScore.setDrawId(lastRecord.getId());
                List<TbRebotScoreRecord> rebotScoreList = tbRebotScoreRecordService.selectTbRebotScoreRecordList(paramRebotScore);
                for (TbRebotScoreRecord score : rebotScoreList ) {
                    if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                        continue;
                    }
                    DrawUtils.calWin(lastRecord,score,roomConfigMap.get(score.getRoomConfigId()));
                    tbRebotScoreRecordService.updateTbRebotScoreRecord(score);

                    double diff = score.getScore().add(score.getWinScore()).doubleValue();
                    if(diff>0){
                        TbRobot robot = tbRobotService.selectTbRobotById(score.getRobotId());
                        TbRobot updateRobot = new TbRobot();
                        updateRobot.setId(score.getRobotId());
                        updateRobot.setLeftScore(robot.getLeftScore().add(new BigDecimal(diff)));
                        tbRobotService.updateTbRobot(updateRobot);
                    }

                }
            }

        }

        TbDrawRecord record = null;
        Date nextRunTime = null;
        record = DrawClientUtils.fetchJndLast();
        if (record!=null){
            nextRunTime = record.getNextOpenTime();
            TbDrawRecord nextRecord = tbDrawRecordService.selectTbDrawRecordByNo(record.getNextPeriodNumber());
            if(nextRecord == null){
                nextRecord = new TbDrawRecord();
                nextRecord.setExpectOpenTime(record.getNextOpenTime());
                nextRecord.setPeriodNumber(record.getNextPeriodNumber());
                nextRecord.setGameType(0);

                if(nextRunTime.getTime()-(30*1000) > new Date().getTime()){
                    nextRecord.setResultStatus(0);
                }else{
                    nextRecord.setResultStatus(1);
                }
                tbDrawRecordService.insertTbDrawRecord(nextRecord);
            }
            Global.jndCurrentDraw = nextRecord;
            Global.jndCurrentNo = record.getNextPeriodNumber();
            Global.jndOpenDate = record.getNextOpenTime();
        }


        if(record == null){
            Date date = new Date();
            Runnable taskPause = () -> {
                System.out.println("初始任务执行中，当前时间: " + date);
                first();     // 递归调度下一次执行
            };
            date.setTime(date.getTime()+10000);
            // 提交任务到线程池
            taskScheduler.schedule(taskPause, date);
            return;
        }

        // 生成随机延迟时间（5-15秒）

        if(nextRunTime.getTime()-(30*1000) > new Date().getTime()){
            Date pauseTime = new Date(nextRunTime.getTime()-(30*1000));
            // 定义任务执行逻辑
            Runnable taskPause = () -> {
                System.out.println("封盘任务执行中，当前时间: " + new Date());
                performPauseLogic();     // 递归调度下一次执行
            };

            // 提交任务到线程池
            taskScheduler.schedule(taskPause, pauseTime);

        }

        // 定义任务执行逻辑
        Runnable task = () -> {
            System.out.println("开奖任务执行中，当前时间: " + new Date());
            scheduleNextExecution();     // 递归调度下一次执行
        };

        long now = new Date().getTime();
        if(nextRunTime.getTime() > now){
            // 提交任务到线程池
            taskScheduler.schedule(task, nextRunTime);
        }else{
            taskScheduler.schedule(task, new Date(now+1000));
        }
    }
    @Transactional
    public TbDrawRecord performDrawLogic() {
        // 替换为实际业务逻辑
        System.out.println("加拿大开奖线程："+new Date().toString());
        //更新当期开奖结果

        TbDrawRecord tbDrawRecord = null;
        TbDrawRecord record = null;
        try{
            Thread.sleep(3000);
            if(!StringUtils.isEmpty(Global.jndCurrentNo)){
                tbDrawRecord = tbDrawRecordService.selectTbDrawRecordByNo(Global.jndCurrentNo);

            }
            boolean alreadyDraw =  tbDrawRecord==null || tbDrawRecord.getResultStatus() == 3;
            if(!alreadyDraw){
                tbDrawRecord.setResultStatus(2);
                tbDrawRecordService.updateTbDrawRecord(tbDrawRecord);
            }
            int count = 1;
            while (true){
                if(count>10){
                    Thread.sleep(1000*60);
                }else{
                    Thread.sleep(2000);
                }

                if(!alreadyDraw){
                    record = DrawClientUtils.fetchJnd(Global.jndCurrentNo);
                }else{
                    record = DrawClientUtils.fetchJndLast();
                }

                if(record==null){
                    long nowTime = new Date().getTime();
                    if(!alreadyDraw &&  nowTime - tbDrawRecord.getExpectOpenTime().getTime() > 1000*60*60*2){
                        if(Global.jndIsOpen){
                            record = DrawClientUtils.fetchJndLast();

                            if(!record.getNextPeriodNumber().equals(Global.jndCurrentNo)){
                                //新增下期开奖结果
                                TbDrawRecord nextRecord = new TbDrawRecord();
                                nextRecord.setExpectOpenTime(record.getNextOpenTime());
                                nextRecord.setPeriodNumber(record.getNextPeriodNumber());
                                nextRecord.setGameType(0);
                                nextRecord.setResultStatus(0);

                                if(nowTime > tbDrawRecord.getExpectOpenTime().getTime()){
                                    nextRecord.setResultStatus(2);
                                }else if(nowTime > tbDrawRecord.getExpectOpenTime().getTime() - 1000*30){
                                    nextRecord.setResultStatus(1);
                                }
                                tbDrawRecordService.insertTbDrawRecord(nextRecord);
                                Global.jndCurrentDraw = nextRecord;
                                Global.jndCurrentNo = record.getNextPeriodNumber();
                                Global.jndOpenDate = record.getNextOpenTime();
                                return record;

                            }
                        }
                    }
                    count++;
                    continue;
                }
                System.out.println("加拿大开奖线程-开奖结果获取时间："+new Date().toString());
                long resultTime = new Date().getTime();
                if(!alreadyDraw){
                    tbDrawRecord.setResultStatus(2);
                    tbDrawRecordService.updateTbDrawRecord(tbDrawRecord);

                    tbDrawRecord.setResult(record.getResult());
                    tbDrawRecord.setResultOne(record.getResultOne());
                    tbDrawRecord.setResultTwo(record.getResultTwo());
                    tbDrawRecord.setResultThree(record.getResultThree());
                    tbDrawRecord.setResultSum(record.getResultSum());
                    tbDrawRecord.setSimpleResult(record.getSimpleResult());
                    tbDrawRecord.setResultStatus(3);
                    tbDrawRecord.setActualOpenTime(new Date());
                    tbDrawRecord.setStatus("0");
                    tbDrawRecord.setDiffSecond((tbDrawRecord.getActualOpenTime().getTime()-tbDrawRecord.getExpectOpenTime().getTime())/1000);
                    tbDrawRecordService.updateTbDrawRecord(tbDrawRecord);
                    Global.jndCurrentNo = null;
                    Global.jndOpenDate = null;

                    if(Global.jndIsOpen){
                        if(record.getNextOpenTime().getTime()<new Date().getTime()){
                            alreadyDraw = true;
                            count++;
                            continue;
                        }
                    }

                }else{
                    if(Global.jndIsOpen){
                        if(record.getNextOpenTime().getTime()<new Date().getTime()){
                            alreadyDraw = true;
                            count++;
                            continue;
                        }
                        TbDrawRecord nextRecord = tbDrawRecordService.selectTbDrawRecordByNo(record.getNextPeriodNumber());
                        if(nextRecord == null){
                            nextRecord = new TbDrawRecord();
                            nextRecord.setExpectOpenTime(record.getNextOpenTime());
                            nextRecord.setPeriodNumber(record.getNextPeriodNumber());
                            nextRecord.setGameType(0);
                            if(record.getNextOpenTime().getTime()-(30*1000) > new Date().getTime()){
                                nextRecord.setResultStatus(0);
                            }else{
                                nextRecord.setResultStatus(1);
                            }
                            tbDrawRecordService.insertTbDrawRecord(nextRecord);
                            Global.jndCurrentDraw = nextRecord;
                        }
                        Global.jndCurrentNo = record.getNextPeriodNumber();
                        Global.jndOpenDate = record.getNextOpenTime();
                    }

                    if(alreadyDraw){
                        if(record.getExpectOpenTime().getTime()<(new Date().getTime())){
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(new Date());
                            cal.add(Calendar.SECOND,210);
                            record.setExpectOpenTime(cal.getTime());
                        }
                        return record;
                    }
                }
                if(Global.jndDrawList.size()<=0 || Integer.parseInt(Global.jndDrawList.get(0).getPeriodNumber())<Integer.parseInt(record.getPeriodNumber())){
                    Global.jndDrawList.add(0,record);
                    if(Global.jndDrawList.size()>10){
                        Global.jndDrawList = new ArrayList<>(Global.jndDrawList.subList(0,10));
                    }
                }
                String historyList = "";
                for (TbDrawRecord obj : Global.jndDrawList) {
                    historyList += obj.getResultSum()+" ";
                }

                List<TbTenantConfig> tenantConfigList = tbTenantConfigService.selectTbTenantConfigList(new TbTenantConfig());
                Map<Long,TbTenantConfig> tenantConfigMap = new HashMap<>();
                for (TbTenantConfig config : tenantConfigList){
                    if(config.getTenantId()==-1){
                        continue;
                    }
                    tenantConfigMap.put(config.getTenantId(),config);
                }

                Map<Long,List<TbScoreRecord>> scoreMap = new HashMap<>();
                List<TbRoomConfig> configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());
                Map<Long,TbRoomConfig> roomConfigMap = new HashMap<>();
                Map<String,TbRoomConfig> roomMap = new HashMap<>();
                for (TbRoomConfig config:configList) {
                    if(!config.getRoomName().contains("加拿大")){
                        continue;
                    }
                    if(config.getTenantId()==-1){
                        continue;
                    }
                    roomConfigMap.put(config.getId(),config);
                    roomMap.put(config.getTenantId()+"-"+config.getRoomName(),config);
                    scoreMap.put(config.getId(),new ArrayList<>());
                }

                //计算投注结果

                TbScoreRecord paramScore = new TbScoreRecord();
                paramScore.setDrawId(tbDrawRecord.getId());
                paramScore.setStatus("0");
                List<TbScoreRecord> scoreList=tbScoreRecordService.selectTbScoreRecordList(paramScore);
                for (TbScoreRecord score : scoreList ) {
                    try {
                        if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                            continue;
                        }
                        DrawUtils.calWin(tbDrawRecord,score,roomConfigMap.get(score.getRoomConfigId()));
                        score.setResult(record.getSimpleResult());
                        score.setResultOne(record.getResultOne());
                        score.setResultTwo(record.getResultTwo());
                        score.setResultThree(record.getResultThree());
                        score.setResultSum(record.getResultSum());
                        tbScoreRecordService.updateTbScoreRecord(score);

                        double diff = score.getScore().add(score.getWinScore()).doubleValue();
                        if(diff>0){
                            SysUser user = sysUserService.selectUserById(score.getUserId());
                            SysUser updateUser = new SysUser(user.getUserId());
                            updateUser.setScore(user.getScore().add(new BigDecimal(diff)));
                            sysUserService.updateUserInfo(updateUser);
                        }

//                        scoreMap.get(score.getRoomConfigId()).add(score);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

                //计算机器人投注结果
                TbRebotScoreRecord paramRebotScore = new TbRebotScoreRecord();
                paramRebotScore.setDrawId(tbDrawRecord.getId());
                List<TbRebotScoreRecord> rebotScoreList = tbRebotScoreRecordService.selectTbRebotScoreRecordList(paramRebotScore);
                for (TbRebotScoreRecord score : rebotScoreList ) {
                    try {
                        if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                            continue;
                        }
                        DrawUtils.calWin(tbDrawRecord,score,roomConfigMap.get(score.getRoomConfigId()));
                        tbRebotScoreRecordService.updateTbRebotScoreRecord(score);

                        double diff = score.getScore().add(score.getWinScore()).doubleValue();
                        if(diff>0){
                            TbRobot robot = tbRobotService.selectTbRobotById(score.getRobotId());
                            TbRobot updateRobot = new TbRobot();
                            updateRobot.setId(score.getRobotId());
                            updateRobot.setLeftScore(robot.getLeftScore().add(new BigDecimal(diff)));
                            tbRobotService.updateTbRobot(updateRobot);
                        }

//                        scoreMap.get(score.getRoomConfigId()).add(score.parseTo());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

                System.out.println("加拿大开奖线程-投注结果计算完成时间："+new Date().toString()+" 耗时："+(new Date().getTime()-resultTime)/1000);

                //推送中奖结果

                TbRobot paramRobot = new TbRobot();
                paramRobot.setStatus("0");
                List<TbRobot> robotList = tbRobotService.selectRobotForScoreFilter(35);
                for(TbRobot robot : robotList){
                    if(robot.getTenantId()==-1){
                        continue;
                    }
                    TbScoreRecord score = new TbScoreRecord();
                    score.setUserName(robot.getName());
                    score.setWinScore(robot.getLeftScore());

                    TbRoomConfig config = roomMap.get(robot.getTenantId()+"-"+robot.getRoomName());
                    if(config != null){
                        scoreMap.get(config.getId()).add(score);
                    }
                }
                List<SysUser> userList = sysUserService.selectUserForScoreFilter(60);
                for (SysUser user:userList ) {
                    TbScoreRecord score = new TbScoreRecord();
                    score.setUserName(user.getUserName());
                    score.setWinScore(user.getScore());
                    for (Long key :scoreMap.keySet()) {
                        TbRoomConfig config = roomConfigMap.get(key);
                        if(config.getTenantId().longValue() == user.getDeptId().longValue()){
                            scoreMap.get(config.getId()).add(score);
                        }
                    }
                }


                List<TbScoreRecord> temp = null;

                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                String space = " ";
                String lineEnd = "<br/>";
                for (Long key :scoreMap.keySet()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(sdf.format(new Date())+space+"第"+tbDrawRecord.getPeriodNumber()+"期"+lineEnd);
                    sb.append("开奖结果："+tbDrawRecord.getResultOne()+"+"+tbDrawRecord.getResultTwo()+"+"+tbDrawRecord.getResultThree()+"="+tbDrawRecord.getResultSum()+space+tbDrawRecord.getSimpleResult()+lineEnd);
                    sb.append("<span class='content_close'>- - - - - - - - - - - - - - - - - - - - - - - - - - - -</span>"+lineEnd);

                    TbRoomConfig config = roomConfigMap.get(key);
                    temp = scoreMap.get(key);
                    if("1".equals(config.getIsOpen()) && (temp==null || temp.size()==0)){
                        continue;
                    }
                    temp.sort((o1, o2) -> {
                        BigDecimal s1 = o1.getWinScore();
                        BigDecimal s2 = o2.getWinScore();

                        // 处理空值（null排最后）
                        if (s1 == null && s2 == null) return 0;
                        if (s1 == null) return 1;
                        if (s2 == null) return -1;

                        // 降序：s2与s1比较（注意顺序）
                        return s2.compareTo(s1);
                    });

                    for(int i = 0;i<temp.size();i++){
                        String val = (temp.get(i).getUserName().length()>2?temp.get(i).getUserName().substring(0,2):temp.get(i).getUserName())+":"+temp.get(i).getWinScore().intValue()+space;
                        if (val.length() < 12) {
                            val = String.format("%-10s", val).replace(" ","&nbsp;&nbsp;");
                        }
                        sb.append(val);
                        if(i%3==2){
                            sb.append(lineEnd);
                        }
                    }
                    sb.append("<span class='content_close'>- - - - - - - - - - - - - - - - - - - - - - - - - - - -</span>");
                    sb.append("近显示1小时内活跃用户的账单"+lineEnd);
                    sb.append("历史: "+historyList);

                    TbChatMessage messageRebot = new TbChatMessage();
                    messageRebot.setSenderId(-1l);
                    messageRebot.setCreatedTime(new Date());
                    messageRebot.setSenderType("1");
                    messageRebot.setTenantId(config.getTenantId());
                    messageRebot.setSenderHeadImageId(tenantConfigMap.get(config.getTenantId()).getRebotImageId());
                    messageRebot.setSenderUserName(tenantConfigMap.get(config.getTenantId()).getRebotName());
                    messageRebot.setGroupId(config.getId());
                    messageRebot.setContent(sb.toString());
                    messageRebot.setDrawId(tbDrawRecord.getId());
                    messageRebot.setRebotMessageType(4);
                    tbChatMessageService.insertTbChatMessage(messageRebot);
                    messagingTemplate.convertAndSend(
                            "/topic/group/" + messageRebot.getGroupId(),
                            messageRebot
                    );
                }

                System.out.println("加拿大开奖线程-推送完成时间："+new Date().toString()+" 耗时："+(new Date().getTime()-resultTime)/1000);
                if(Global.jndIsOpen){
                    //新增下期开奖结果
                    TbDrawRecord nextRecord = new TbDrawRecord();
                    nextRecord.setExpectOpenTime(record.getNextOpenTime());
                    nextRecord.setPeriodNumber(record.getNextPeriodNumber());
                    nextRecord.setGameType(0);
                    nextRecord.setResultStatus(0);
                    tbDrawRecordService.insertTbDrawRecord(nextRecord);
                    Global.jndCurrentDraw = nextRecord;
                    Global.jndCurrentNo = record.getNextPeriodNumber();
                    Global.jndOpenDate = record.getNextOpenTime();
                }

                break;
            }
        }catch (Exception e){
            e.printStackTrace();
            if(record == null){
                record = new TbDrawRecord();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.SECOND,210);
            record.setExpectOpenTime(cal.getTime());
        }


        return record;
    }

    @Transactional
    public TbDrawRecord performPauseLogic() {
        // 替换为实际业务逻辑
        System.out.println("加拿大封盘线程："+new Date().toString());
        TbDrawRecord tbDrawRecord = tbDrawRecordService.selectTbDrawRecordByNo(Global.jndCurrentNo);
        Global.jndCurrentDraw.setResultStatus(1);

        List<TbTenantConfig> tenantConfigList = tbTenantConfigService.selectTbTenantConfigList(new TbTenantConfig());
        Map<Long,TbTenantConfig> tenantConfigMap = new HashMap<>();
        for (TbTenantConfig config : tenantConfigList){
            if(config.getTenantId()==-1){
                continue;
            }
            tenantConfigMap.put(config.getTenantId(),config);
        }

        Map<Long,List<TbScoreRecord>> scoreMap = new HashMap<>();
        List<TbRoomConfig> configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());
        Map<Long,TbRoomConfig> roomConfigMap = new HashMap<>();
        for (TbRoomConfig config:configList) {
            if(config.getTenantId()==-1){
                continue;
            }
            roomConfigMap.put(config.getId(),config);
            scoreMap.put(config.getId(),new ArrayList<>());
        }

        TbScoreRecord paramScore = new TbScoreRecord();
        paramScore.setDrawId(tbDrawRecord.getId());
        paramScore.setStatus("0");
        List<TbScoreRecord> scoreList=tbScoreRecordService.selectTbScoreRecordList(paramScore);
        for (TbScoreRecord score : scoreList ) {
            if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                continue;
            }

            scoreMap.get(score.getRoomConfigId()).add(score);
        }

        TbRebotScoreRecord paramRebotScore = new TbRebotScoreRecord();
        paramRebotScore.setDrawId(tbDrawRecord.getId());
        List<TbRebotScoreRecord> rebotScoreList = tbRebotScoreRecordService.selectTbRebotScoreRecordList(paramRebotScore);
        for (TbRebotScoreRecord score : rebotScoreList ) {
            if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                continue;
            }

            scoreMap.get(score.getRoomConfigId()).add(score.parseTo());
        }

        //推送投注结果
        List<TbScoreRecord> temp = null;

        String space = " ";
        String lineEnd = "<br/>";
        for (Long key :scoreMap.keySet()) {
            TbRoomConfig config = roomConfigMap.get(key);
            if(!config.getRoomName().contains("加拿大")){
                continue;
            }
            temp = scoreMap.get(key);
            temp.sort(Comparator.comparing(TbScoreRecord::getCreateTime));

            StringBuilder sb = new StringBuilder();
            sb.append("<span class='content_close'>- - - - - - - - - - - - - - 以下数据为准- - - - - - - - - - - - - -</span>"+lineEnd);
            for(int i = 0;i<temp.size();i++){
                sb.append(temp.get(i).getUserName()+space+"投："+temp.get(i).getBetting()+space+"余："+temp.get(i).getLeftScore().intValue()+lineEnd);
            }
            sb.append("<span class='content_close'>- - - - - - - - - - - - - - - -  - - - -  - - - - - - - - - - - - - - - -</span>"+lineEnd);
            sb.append("本平台严禁卡红、刷水等恶意套利行为。一经发现，将扣除该账号在本平台历史所有赠送的积分进行结账，或更严厉的处罚。");

            TbChatMessage messageRebot = new TbChatMessage();
            messageRebot.setSenderId(-1l);
            messageRebot.setCreatedTime(new Date());
            messageRebot.setSenderType("1");
            messageRebot.setTenantId(config.getTenantId());
            messageRebot.setSenderHeadImageId(tenantConfigMap.get(config.getTenantId()).getRebotImageId());
            messageRebot.setSenderUserName(tenantConfigMap.get(config.getTenantId()).getRebotName());
            messageRebot.setGroupId(config.getId());
            messageRebot.setContent(sb.toString());
            messageRebot.setDrawId(tbDrawRecord.getId());
            messageRebot.setRebotMessageType(3);
            tbChatMessageService.insertTbChatMessage(messageRebot);
            messagingTemplate.convertAndSend(
                    "/topic/group/" + messageRebot.getGroupId(),
                    messageRebot
            );
        }
        return new TbDrawRecord();
    }
}
