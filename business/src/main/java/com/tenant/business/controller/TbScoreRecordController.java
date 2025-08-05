package com.tenant.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenant.business.domain.statistics.PersonWinRecord;
import com.tenant.business.domain.statistics.ScoreSummaryRecord;
import com.tenant.business.domain.vo.KValue;
import com.tenant.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbScoreRecord;
import com.tenant.business.service.ITbScoreRecordService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 投注记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/ScoreRecord")
public class TbScoreRecordController extends BaseController
{
    private String prefix = "business/ScoreRecord";

    @Autowired
    private ITbScoreRecordService tbScoreRecordService;

    @RequiresPermissions("business:ScoreRecord:view")
    @GetMapping()
    public String ScoreRecord(TbScoreRecord tbScoreRecord, Model model)
    {
        if(tbScoreRecord!=null){
            model.addAttribute("periodNumber",tbScoreRecord.getPeriodNumber());
            model.addAttribute("userId",tbScoreRecord.getUserId());
            model.addAttribute("beginCreateTime",tbScoreRecord.getBeginCreateTime());
            model.addAttribute("endCreateTime",tbScoreRecord.getEndCreateTime());
        }
        return prefix + "/ScoreRecord";
    }

    @RequiresPermissions("business:ScoreSummary:view")
    @GetMapping("/ScoreSummary")
    public String ScoreSummary()
    {
        return prefix + "/ScoreSummary";
    }

    @RequiresPermissions("business:ScoreSummary:view")
    @PostMapping("/statisticsPersonRecord")
    @ResponseBody
    public AjaxResult statisticsPersonRecord(TbScoreRecord tbScoreRecord)
    {
        Map<String, Object> map = tbScoreRecordService.statisticsPersonRecord(tbScoreRecord);
        return AjaxResult.success(map);
    }

    @RequiresPermissions("business:ScoreSummary:view")
    @PostMapping("/ScoreSummary")
    @ResponseBody
    public AjaxResult ScoreSummary(TbScoreRecord tbScoreRecord)
    {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = tbScoreRecordService.statisticsDashboardRecord(tbScoreRecord);
        result.put("scorePerson",map.get("scorePerson"));
        result.put("totalWin",0-Double.parseDouble(map.get("totalWin").toString()));
        List<KValue> list = new ArrayList<>();
        list.add(new KValue("加拿大2.0",map.get("totalJnd20Win").toString()));
        list.add(new KValue("加拿大2.8",map.get("totalJnd28Win").toString()));
        list.add(new KValue("加拿大3.0",map.get("totalJnd30Win").toString()));
        list.add(new KValue("加拿大4.0",map.get("totalJnd40Win").toString()));
        list.add(new KValue("台湾2.0",map.get("totalTw20Win").toString()));
        list.add(new KValue("台湾2.8",map.get("totalTw28Win").toString()));
        list.add(new KValue("台湾3.0",map.get("totalTw30Win").toString()));
        list.add(new KValue("台湾4.0",map.get("totalTw40Win").toString()));
        result.put("list",list);
        return AjaxResult.success(result);
    }


    @PostMapping("/backScore")
    @ResponseBody
    public AjaxResult backScore(@RequestParam HashMap<String, String> map)
    {
        String hhDate = map.get("hhDate");
        String roomName = map.get("roomName");
        String type = map.get("type");
        Long tenantId = getTenantId();
        if("1".equals(type)){
            double hh1 = Double.parseDouble(map.get("hh1"));
            double hh2 = Double.parseDouble(map.get("hh2"));
            double hh3 = Double.parseDouble(map.get("hh3"))/100;
            tbScoreRecordService.insertHuiHongOrder(hhDate,hh1,hh2,hh3,tenantId);
            tbScoreRecordService.updateHuiHongUser(hhDate,hh1,hh2,hh3,tenantId);
        }
        if("2".equals(type)){
            double ls1 = Double.parseDouble(map.get("ls1"));
            double ls2 = Double.parseDouble(map.get("ls2"));
            double ls3 = Double.parseDouble(map.get("ls3"))/100;
            tbScoreRecordService.insertLiuShuiOrder(hhDate,ls1,ls2,ls3,tenantId);
            tbScoreRecordService.updateLiuShuiUser(hhDate,ls1,ls2,ls3,tenantId);
        }
        if("3".equals(type)){
            double bs1 = Double.parseDouble(map.get("bs1"));
            double bs2 = Double.parseDouble(map.get("bs2"));
            double bs3 = Double.parseDouble(map.get("bs3"))/100;
            tbScoreRecordService.insertBaShuOrder(hhDate,bs1,bs2,bs3,tenantId);
            tbScoreRecordService.updateBaShuUser(hhDate,bs1,bs2,bs3,tenantId);
        }

        return AjaxResult.success(1);
    }

    @PostMapping("/personWinRecordList")
    @ResponseBody
    public TableDataInfo personWinRecordList(TbScoreRecord tbScoreRecord)
    {

        startPage();
        List<PersonWinRecord> list = tbScoreRecordService.personWinRecord(tbScoreRecord);
        return getDataTable(list);
    }

    @RequiresPermissions("business:ScoreSummary:list")
    @PostMapping("/scoreSummaryList")
    @ResponseBody
    public TableDataInfo scoreSummaryList(TbScoreRecord tbScoreRecord)
    {

        startPage();
        List<ScoreSummaryRecord> list = tbScoreRecordService.scoreSummaryList(tbScoreRecord);
        return getDataTable(list);
    }

    /**
     * 查询投注记录列表
     */
    @RequiresPermissions("business:ScoreRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbScoreRecord tbScoreRecord)
    {
        startPage();
        List<TbScoreRecord> list = tbScoreRecordService.selectTbScoreRecordList(tbScoreRecord);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public TableDataInfo listForApp( TbScoreRecord tbScoreRecord)
    {
        startPage();
        tbScoreRecord.setTenantId(getTenantId());
        tbScoreRecord.setUserId(ShiroUtils.getUserId());
        List<TbScoreRecord> list = tbScoreRecordService.selectTbScoreRecordList(tbScoreRecord);
        return getDataTable(list);
    }

    /**
     * 导出投注记录列表
     */
    @RequiresPermissions("business:ScoreRecord:export")
    @Log(title = "投注记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbScoreRecord tbScoreRecord)
    {
        List<TbScoreRecord> list = tbScoreRecordService.selectTbScoreRecordList(tbScoreRecord);
        ExcelUtil<TbScoreRecord> util = new ExcelUtil<TbScoreRecord>(TbScoreRecord.class);
        return util.exportExcel(list, "投注记录数据");
    }

    /**
     * 新增投注记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存投注记录
     */
    @RequiresPermissions("business:ScoreRecord:add")
    @Log(title = "投注记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbScoreRecord tbScoreRecord)
    {
        return toAjax(tbScoreRecordService.insertTbScoreRecord(tbScoreRecord));
    }

    /**
     * 修改投注记录
     */
    @RequiresPermissions("business:ScoreRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbScoreRecord tbScoreRecord = tbScoreRecordService.selectTbScoreRecordById(id);
        mmap.put("tbScoreRecord", tbScoreRecord);
        return prefix + "/edit";
    }
    /**
     * 详情投注记录
     */
    @RequiresPermissions("business:ScoreRecord:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbScoreRecord tbScoreRecord = tbScoreRecordService.selectTbScoreRecordById(id);
        return success(tbScoreRecord);
    }

    /**
     * 修改保存投注记录
     */
    @RequiresPermissions("business:ScoreRecord:edit")
    @Log(title = "投注记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbScoreRecord tbScoreRecord)
    {
        return toAjax(tbScoreRecordService.updateTbScoreRecord(tbScoreRecord));
    }

    /**
     * 删除投注记录
     */
    @RequiresPermissions("business:ScoreRecord:remove")
    @Log(title = "投注记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbScoreRecordService.deleteTbScoreRecordByIds(ids));
    }
}
