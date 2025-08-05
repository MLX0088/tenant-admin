package com.tenant.business.controller;

import java.util.List;
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
import com.tenant.business.domain.TbRebotScoreRecord;
import com.tenant.business.service.ITbRebotScoreRecordService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 机器人投注记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/RebotScoreRecord")
public class TbRebotScoreRecordController extends BaseController
{
    private String prefix = "business/RebotScoreRecord";

    @Autowired
    private ITbRebotScoreRecordService tbRebotScoreRecordService;

    @RequiresPermissions("business:RebotScoreRecord:view")
    @GetMapping()
    public String RebotScoreRecord()
    {
        return prefix + "/RebotScoreRecord";
    }

    /**
     * 查询机器人投注记录列表
     */
    @RequiresPermissions("business:RebotScoreRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRebotScoreRecord tbRebotScoreRecord)
    {
        startPage();
        List<TbRebotScoreRecord> list = tbRebotScoreRecordService.selectTbRebotScoreRecordList(tbRebotScoreRecord);
        return getDataTable(list);
    }

    /**
     * 导出机器人投注记录列表
     */
    @RequiresPermissions("business:RebotScoreRecord:export")
    @Log(title = "机器人投注记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRebotScoreRecord tbRebotScoreRecord)
    {
        List<TbRebotScoreRecord> list = tbRebotScoreRecordService.selectTbRebotScoreRecordList(tbRebotScoreRecord);
        ExcelUtil<TbRebotScoreRecord> util = new ExcelUtil<TbRebotScoreRecord>(TbRebotScoreRecord.class);
        return util.exportExcel(list, "机器人投注记录数据");
    }

    /**
     * 新增机器人投注记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机器人投注记录
     */
    @RequiresPermissions("business:RebotScoreRecord:add")
    @Log(title = "机器人投注记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRebotScoreRecord tbRebotScoreRecord)
    {
        return toAjax(tbRebotScoreRecordService.insertTbRebotScoreRecord(tbRebotScoreRecord));
    }

    /**
     * 修改机器人投注记录
     */
    @RequiresPermissions("business:RebotScoreRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRebotScoreRecord tbRebotScoreRecord = tbRebotScoreRecordService.selectTbRebotScoreRecordById(id);
        mmap.put("tbRebotScoreRecord", tbRebotScoreRecord);
        return prefix + "/edit";
    }
    /**
     * 详情机器人投注记录
     */
    @RequiresPermissions("business:RebotScoreRecord:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRebotScoreRecord tbRebotScoreRecord = tbRebotScoreRecordService.selectTbRebotScoreRecordById(id);
        return success(tbRebotScoreRecord);
    }

    /**
     * 修改保存机器人投注记录
     */
    @RequiresPermissions("business:RebotScoreRecord:edit")
    @Log(title = "机器人投注记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRebotScoreRecord tbRebotScoreRecord)
    {
        return toAjax(tbRebotScoreRecordService.updateTbRebotScoreRecord(tbRebotScoreRecord));
    }

    /**
     * 删除机器人投注记录
     */
    @RequiresPermissions("business:RebotScoreRecord:remove")
    @Log(title = "机器人投注记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRebotScoreRecordService.deleteTbRebotScoreRecordByIds(ids));
    }
}
