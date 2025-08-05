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
import com.tenant.business.domain.TbIpBlack;
import com.tenant.business.service.ITbIpBlackService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * IP黑名单Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/IpBlack")
public class TbIpBlackController extends BaseController
{
    private String prefix = "business/IpBlack";

    @Autowired
    private ITbIpBlackService tbIpBlackService;

    @RequiresPermissions("business:IpBlack:view")
    @GetMapping()
    public String IpBlack()
    {
        return prefix + "/IpBlack";
    }

    /**
     * 查询IP黑名单列表
     */
    @RequiresPermissions("business:IpBlack:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbIpBlack tbIpBlack)
    {
        startPage();
        List<TbIpBlack> list = tbIpBlackService.selectTbIpBlackList(tbIpBlack);
        return getDataTable(list);
    }

    /**
     * 导出IP黑名单列表
     */
    @RequiresPermissions("business:IpBlack:export")
    @Log(title = "IP黑名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbIpBlack tbIpBlack)
    {
        List<TbIpBlack> list = tbIpBlackService.selectTbIpBlackList(tbIpBlack);
        ExcelUtil<TbIpBlack> util = new ExcelUtil<TbIpBlack>(TbIpBlack.class);
        return util.exportExcel(list, "IP黑名单数据");
    }

    /**
     * 新增IP黑名单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存IP黑名单
     */
    @RequiresPermissions("business:IpBlack:add")
    @Log(title = "IP黑名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbIpBlack tbIpBlack)
    {
        return toAjax(tbIpBlackService.insertTbIpBlack(tbIpBlack));
    }

    /**
     * 修改IP黑名单
     */
    @RequiresPermissions("business:IpBlack:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbIpBlack tbIpBlack = tbIpBlackService.selectTbIpBlackById(id);
        mmap.put("tbIpBlack", tbIpBlack);
        return prefix + "/edit";
    }
    /**
     * 详情IP黑名单
     */
    @RequiresPermissions("business:IpBlack:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbIpBlack tbIpBlack = tbIpBlackService.selectTbIpBlackById(id);
        return success(tbIpBlack);
    }

    /**
     * 修改保存IP黑名单
     */
    @RequiresPermissions("business:IpBlack:edit")
    @Log(title = "IP黑名单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbIpBlack tbIpBlack)
    {
        return toAjax(tbIpBlackService.updateTbIpBlack(tbIpBlack));
    }

    /**
     * 删除IP黑名单
     */
    @RequiresPermissions("business:IpBlack:remove")
    @Log(title = "IP黑名单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbIpBlackService.deleteTbIpBlackByIds(ids));
    }
}
