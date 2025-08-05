package com.tenant.business.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tenant.business.service.ITbDrawRecordService;
import com.tenant.business.service.ITbOrderRecordService;
import com.tenant.business.service.ITbScoreRecordService;
import com.tenant.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbWinReport;
import com.tenant.business.service.ITbWinReportService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 盈亏信息Controller
 * 
 * @author luanyu
 * @date 2025-05-05
 */
@Controller
@RequestMapping("/business/WinReport")
public class TbWinReportController extends BaseController
{
    private String prefix = "business/WinReport";

    @Autowired
    private ITbWinReportService tbWinReportService;
    @Autowired
    private ITbOrderRecordService tbOrderRecordService;
    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;

    @RequiresPermissions("business:WinReport:view")
    @GetMapping()
    public String WinReport()
    {
        return prefix + "/WinReport";
    }

    @RequiresPermissions("business:ClearData:delete")
    @GetMapping("ClearData")
    public String ClearData()
    {
        return prefix + "/ClearData";
    }

    @RequiresPermissions("business:ClearData:delete")
    @Log(title = "清除数据", businessType = BusinessType.DELETE)
    @PostMapping("/clear")
    @ResponseBody
    public AjaxResult clear(String startTime,String endTime,String type)
    {
        String[] s = type.split(",");
        for (String str: s ) {
            switch (str){
                case "2" : tbOrderRecordService.deleteByRange(startTime,endTime,getTenantId());break;
                case "3" : tbDrawRecordService.deleteByRange(startTime,endTime,getTenantId());break;
                case "4" : tbScoreRecordService.deleteByRange(startTime,endTime,getTenantId());break;
                case "5" : tbWinReportService.deleteByRange(startTime,endTime,getTenantId());break;
            }
        }
        return toAjax(1);
    }
    /**
     * 查询盈亏信息列表
     */
    @RequiresPermissions("business:WinReport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbWinReport tbWinReport)
    {
        startPage();
        List<TbWinReport> list = tbWinReportService.selectTbWinReportList(tbWinReport);
        return getDataTable(list);
    }

    @GetMapping("/genWinDataDaily")
    @ResponseBody
    public AjaxResult genWinDataDaily(String date)
    {
        if(StringUtils.isEmpty(date)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(new Date());
        }
        int count = tbWinReportService.getDataCount(date);
        if(count > 0){
            return error("执行失败："+date+" 已经存在数据");
        }
        tbWinReportService.genWinDataDaily(date);
        return success();
    }

    /**
     * 导出盈亏信息列表
     */
    @RequiresPermissions("business:WinReport:export")
    @Log(title = "盈亏信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbWinReport tbWinReport)
    {
        List<TbWinReport> list = tbWinReportService.selectTbWinReportList(tbWinReport);
        ExcelUtil<TbWinReport> util = new ExcelUtil<TbWinReport>(TbWinReport.class);
        return util.exportExcel(list, "盈亏信息数据");
    }

    /**
     * 新增盈亏信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存盈亏信息
     */
    @RequiresPermissions("business:WinReport:add")
    @Log(title = "盈亏信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbWinReport tbWinReport)
    {
        return toAjax(tbWinReportService.insertTbWinReport(tbWinReport));
    }

    /**
     * 修改盈亏信息
     */
    @RequiresPermissions("business:WinReport:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbWinReport tbWinReport = tbWinReportService.selectTbWinReportById(id);
        mmap.put("tbWinReport", tbWinReport);
        return prefix + "/edit";
    }
    /**
     * 详情盈亏信息
     */
    @RequiresPermissions("business:WinReport:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbWinReport tbWinReport = tbWinReportService.selectTbWinReportById(id);
        return success(tbWinReport);
    }

    /**
     * 修改保存盈亏信息
     */
    @RequiresPermissions("business:WinReport:edit")
    @Log(title = "盈亏信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbWinReport tbWinReport)
    {
        return toAjax(tbWinReportService.updateTbWinReport(tbWinReport));
    }

    /**
     * 删除盈亏信息
     */
    @RequiresPermissions("business:WinReport:remove")
    @Log(title = "盈亏信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbWinReportService.deleteTbWinReportByIds(ids));
    }
}
