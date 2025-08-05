package com.tenant.system.controller;

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
import com.tenant.system.domain.SysAd;
import com.tenant.system.service.ISysAdService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 广告管理Controller
 * 
 * @author luanyu
 * @date 2023-03-14
 */
@Controller
@RequestMapping("/common/ad")
public class SysAdController extends BaseController
{
    private String prefix = "common/ad";

    @Autowired
    private ISysAdService sysAdService;

    @RequiresPermissions("common:ad:view")
    @GetMapping()
    public String ad()
    {
        return prefix + "/ad";
    }

    /**
     * 查询广告管理列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysAd sysAd)
    {
        startPage();
        List<SysAd> list = sysAdService.selectSysAdList(sysAd);
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @RequiresPermissions("common:ad:export")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysAd sysAd)
    {
        List<SysAd> list = sysAdService.selectSysAdList(sysAd);
        ExcelUtil<SysAd> util = new ExcelUtil<SysAd>(SysAd.class);
        return util.exportExcel(list, "广告管理数据");
    }

    /**
     * 新增广告管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告管理
     */
    @RequiresPermissions("common:ad:add")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAd sysAd)
    {
        return toAjax(sysAdService.insertSysAd(sysAd));
    }

    /**
     * 修改广告管理
     */
    @RequiresPermissions("common:ad:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysAd sysAd = sysAdService.selectSysAdById(id);
        mmap.put("sysAd", sysAd);
        return prefix + "/edit";
    }
    /**
     * 详情广告管理
     */
    @RequiresPermissions("common:ad:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysAd sysAd = sysAdService.selectSysAdById(id);
        return success(sysAd);
    }

    /**
     * 修改保存广告管理
     */
    @RequiresPermissions("common:ad:edit")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysAd sysAd)
    {
        return toAjax(sysAdService.updateSysAd(sysAd));
    }

    /**
     * 删除广告管理
     */
    @RequiresPermissions("common:ad:remove")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAdService.deleteSysAdByIds(ids));
    }
}
