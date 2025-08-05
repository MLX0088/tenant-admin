package com.tenant.business.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawClientUtils;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 手动开奖记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/DrawManual")
public class TbDrawManualController extends BaseController
{
    private String prefix = "business/DrawManual";

    @Autowired
    private ITbDrawManualService tbDrawManualService;
    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITbRobotService tbRobotService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;
    @Autowired
    private ITbRebotScoreRecordService tbRebotScoreRecordService;

    @RequiresPermissions("business:DrawManual:view")
    @GetMapping()
    public String DrawManual()
    {
        return prefix + "/DrawManual";
    }

    /**
     * 查询手动开奖记录列表
     */
    @RequiresPermissions("business:DrawManual:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbDrawManual tbDrawManual)
    {
        startPage();
        List<TbDrawManual> list = tbDrawManualService.selectTbDrawManualList(tbDrawManual);
        return getDataTable(list);
    }

    /**
     * 导出手动开奖记录列表
     */
    @RequiresPermissions("business:DrawManual:export")
    @Log(title = "手动开奖记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbDrawManual tbDrawManual)
    {
        List<TbDrawManual> list = tbDrawManualService.selectTbDrawManualList(tbDrawManual);
        ExcelUtil<TbDrawManual> util = new ExcelUtil<TbDrawManual>(TbDrawManual.class);
        return util.exportExcel(list, "手动开奖记录数据");
    }

    /**
     * 新增手动开奖记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存手动开奖记录
     */
    @RequiresPermissions("business:DrawManual:add")
    @Log(title = "手动开奖记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(TbDrawManual tbDrawManual)
    {
        try{
            TbDrawRecord lastRecord = null;
            if("加拿大28".equals(tbDrawManual.getRoomName())){
                lastRecord = tbDrawRecordService.selectLastTbDrawRecordByGameType(0);
            }else{
                lastRecord = tbDrawRecordService.selectLastTbDrawRecordByGameType(2);
            }


            TbDrawRecord record = tbDrawRecordService.selectTbDrawRecordByNo(tbDrawManual.getPeriodNumber());
            if(record == null){
                record = new TbDrawRecord();
            }else{
                if(record.getResultStatus()==3){
                    return AjaxResult.error("手动开奖失败，该期已开奖");
                }
            }

            record.setResultOne(Math.toIntExact(tbDrawManual.getFirst()));
            record.setResultTwo(Math.toIntExact(tbDrawManual.getSecond()));
            record.setResultThree(Math.toIntExact(tbDrawManual.getThird()));
            record.setResultSum(Math.toIntExact(tbDrawManual.getFirst()+tbDrawManual.getSecond()+tbDrawManual.getThird()));
            record.setPeriodNumber(tbDrawManual.getPeriodNumber());
            DrawUtils.calDrawWithResult(record);
            record.setResultStatus(3);
            record.setActualOpenTime(new Date());
            record.setStatus("0");
            if(record.getExpectOpenTime() != null){
                record.setDiffSecond((record.getActualOpenTime().getTime()-record.getExpectOpenTime().getTime())/1000);
            }

            if(record.getId() != null && record.getId()>0){
                tbDrawRecordService.updateTbDrawRecord(record);

                List<TbRoomConfig> configList = tbRoomConfigService.selectTbRoomConfigList(new TbRoomConfig());
                Map<Long,TbRoomConfig> roomConfigMap = new HashMap<>();
                for (TbRoomConfig config:configList) {
                    roomConfigMap.put(config.getId(),config);
                }

                //计算投注结果

                TbScoreRecord paramScore = new TbScoreRecord();
                paramScore.setDrawId(record.getId());
                paramScore.setStatus("0");
                List<TbScoreRecord> scoreList=tbScoreRecordService.selectTbScoreRecordList(paramScore);
                for (TbScoreRecord score : scoreList ) {
                    if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                        continue;
                    }
                    DrawUtils.calWin(record,score,roomConfigMap.get(score.getRoomConfigId()));
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

                }

                //计算机器人投注结果
                TbRebotScoreRecord paramRebotScore = new TbRebotScoreRecord();
                paramRebotScore.setDrawId(record.getId());
                List<TbRebotScoreRecord> rebotScoreList = tbRebotScoreRecordService.selectTbRebotScoreRecordList(paramRebotScore);
                for (TbRebotScoreRecord score : rebotScoreList ) {
                    try{
                        if(!roomConfigMap.containsKey(score.getRoomConfigId())){
                            continue;
                        }
                        DrawUtils.calWin(record,score,roomConfigMap.get(score.getRoomConfigId()));
                        tbRebotScoreRecordService.updateTbRebotScoreRecord(score);

                        double diff = score.getScore().add(score.getWinScore()).doubleValue();
                        if(diff>0){
                            TbRobot robot = tbRobotService.selectTbRobotById(score.getRobotId());
                            TbRobot updateRobot = new TbRobot();
                            updateRobot.setId(score.getRobotId());
                            updateRobot.setLeftScore(robot.getLeftScore().add(new BigDecimal(diff)));
                            tbRobotService.updateTbRobot(updateRobot);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            }else{

                if("加拿大28".equals(tbDrawManual.getRoomName())){
                    record.setGameType(0);
                }else if("台湾28".equals(tbDrawManual.getRoomName())){
                    record.setGameType(2);
                }
                tbDrawRecordService.insertTbDrawRecord(record);
            }



            if(lastRecord.getPeriodNumber().equals(tbDrawManual.getPeriodNumber())){
                Date nextRunTime = null;
                if(lastRecord.getGameType() == 0){
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
                } else if(lastRecord.getGameType() == 2){
                    TbDrawRecord nextRecord = tbDrawRecordService.selectTbDrawRecordByNo(record.getNextPeriodNumber());
                    if(nextRecord == null){
                        nextRecord = new TbDrawRecord();
                        nextRecord.setExpectOpenTime(record.getNextOpenTime());
                        nextRecord.setPeriodNumber(record.getNextPeriodNumber());
                        nextRecord.setGameType(2);
                        if(nextRunTime.getTime()-(30*1000) > new Date().getTime()){
                            nextRecord.setResultStatus(0);
                        }else{
                            nextRecord.setResultStatus(1);
                        }
                        tbDrawRecordService.insertTbDrawRecord(nextRecord);
                    }
                    Global.twCurrentDraw = nextRecord;
                    Global.twCurrentNo = record.getNextPeriodNumber();
                    Global.twOpenDate = record.getNextOpenTime();
                }

            }

            tbDrawManualService.insertTbDrawManual(tbDrawManual);

            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }


        return AjaxResult.error("手动开奖失败");
    }

    /**
     * 修改手动开奖记录
     */
    @RequiresPermissions("business:DrawManual:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbDrawManual tbDrawManual = tbDrawManualService.selectTbDrawManualById(id);
        mmap.put("tbDrawManual", tbDrawManual);
        return prefix + "/edit";
    }
    /**
     * 详情手动开奖记录
     */
    @RequiresPermissions("business:DrawManual:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbDrawManual tbDrawManual = tbDrawManualService.selectTbDrawManualById(id);
        return success(tbDrawManual);
    }

    /**
     * 修改保存手动开奖记录
     */
    @RequiresPermissions("business:DrawManual:edit")
    @Log(title = "手动开奖记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbDrawManual tbDrawManual)
    {
        return toAjax(tbDrawManualService.updateTbDrawManual(tbDrawManual));
    }

    /**
     * 删除手动开奖记录
     */
    @RequiresPermissions("business:DrawManual:remove")
    @Log(title = "手动开奖记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbDrawManualService.deleteTbDrawManualByIds(ids));
    }
}
