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
import com.tenant.business.domain.TbQuickReply;
import com.tenant.business.service.ITbQuickReplyService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 快捷语管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/QuickReply")
public class TbQuickReplyController extends BaseController
{
    private String prefix = "business/QuickReply";

    @Autowired
    private ITbQuickReplyService tbQuickReplyService;

    @RequiresPermissions("business:QuickReply:view")
    @GetMapping()
    public String QuickReply()
    {
        return prefix + "/QuickReply";
    }

    /**
     * 查询快捷语管理列表
     */
    @RequiresPermissions("business:QuickReply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbQuickReply tbQuickReply)
    {
        startPage();
        List<TbQuickReply> list = tbQuickReplyService.selectTbQuickReplyList(tbQuickReply);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        TbQuickReply tbQuickReply = new TbQuickReply();
        tbQuickReply.setTenantId(getTenantId());
        List<TbQuickReply> list = tbQuickReplyService.selectTbQuickReplyList(tbQuickReply);
        return AjaxResult.success(list);
    }

    /**
     * 导出快捷语管理列表
     */
    @RequiresPermissions("business:QuickReply:export")
    @Log(title = "快捷语管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbQuickReply tbQuickReply)
    {
        List<TbQuickReply> list = tbQuickReplyService.selectTbQuickReplyList(tbQuickReply);
        ExcelUtil<TbQuickReply> util = new ExcelUtil<TbQuickReply>(TbQuickReply.class);
        return util.exportExcel(list, "快捷语管理数据");
    }

    /**
     * 新增快捷语管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存快捷语管理
     */
    @RequiresPermissions("business:QuickReply:add")
    @Log(title = "快捷语管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbQuickReply tbQuickReply)
    {
        return toAjax(tbQuickReplyService.insertTbQuickReply(tbQuickReply));
    }

    /**
     * 修改快捷语管理
     */
    @RequiresPermissions("business:QuickReply:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbQuickReply tbQuickReply = tbQuickReplyService.selectTbQuickReplyById(id);
        mmap.put("tbQuickReply", tbQuickReply);
        return prefix + "/edit";
    }
    /**
     * 详情快捷语管理
     */
    @RequiresPermissions("business:QuickReply:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbQuickReply tbQuickReply = tbQuickReplyService.selectTbQuickReplyById(id);
        return success(tbQuickReply);
    }

    /**
     * 修改保存快捷语管理
     */
    @RequiresPermissions("business:QuickReply:edit")
    @Log(title = "快捷语管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbQuickReply tbQuickReply)
    {
        return toAjax(tbQuickReplyService.updateTbQuickReply(tbQuickReply));
    }

    /**
     * 删除快捷语管理
     */
    @RequiresPermissions("business:QuickReply:remove")
    @Log(title = "快捷语管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbQuickReplyService.deleteTbQuickReplyByIds(ids));
    }
}
