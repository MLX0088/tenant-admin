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
import com.tenant.business.domain.TbOperLogs;
import com.tenant.business.service.ITbOperLogsService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 操作记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/OperLogs")
public class TbOperLogsController extends BaseController
{
    private String prefix = "business/OperLogs";

    @Autowired
    private ITbOperLogsService tbOperLogsService;

    @RequiresPermissions("business:OperLogs:view")
    @GetMapping()
    public String OperLogs()
    {
        return prefix + "/OperLogs";
    }

    /**
     * 查询操作记录列表
     */
    @RequiresPermissions("business:OperLogs:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbOperLogs tbOperLogs)
    {
        startPage();
        List<TbOperLogs> list = tbOperLogsService.selectTbOperLogsList(tbOperLogs);
        return getDataTable(list);
    }

    /**
     * 导出操作记录列表
     */
    @RequiresPermissions("business:OperLogs:export")
    @Log(title = "操作记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbOperLogs tbOperLogs)
    {
        List<TbOperLogs> list = tbOperLogsService.selectTbOperLogsList(tbOperLogs);
        ExcelUtil<TbOperLogs> util = new ExcelUtil<TbOperLogs>(TbOperLogs.class);
        return util.exportExcel(list, "操作记录数据");
    }

    /**
     * 新增操作记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存操作记录
     */
    @RequiresPermissions("business:OperLogs:add")
    @Log(title = "操作记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbOperLogs tbOperLogs)
    {
        return toAjax(tbOperLogsService.insertTbOperLogs(tbOperLogs));
    }

    /**
     * 修改操作记录
     */
    @RequiresPermissions("business:OperLogs:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbOperLogs tbOperLogs = tbOperLogsService.selectTbOperLogsById(id);
        mmap.put("tbOperLogs", tbOperLogs);
        return prefix + "/edit";
    }
    /**
     * 详情操作记录
     */
    @RequiresPermissions("business:OperLogs:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbOperLogs tbOperLogs = tbOperLogsService.selectTbOperLogsById(id);
        return success(tbOperLogs);
    }

    /**
     * 修改保存操作记录
     */
    @RequiresPermissions("business:OperLogs:edit")
    @Log(title = "操作记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbOperLogs tbOperLogs)
    {
        return toAjax(tbOperLogsService.updateTbOperLogs(tbOperLogs));
    }

    /**
     * 删除操作记录
     */
    @RequiresPermissions("business:OperLogs:remove")
    @Log(title = "操作记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbOperLogsService.deleteTbOperLogsByIds(ids));
    }
}
