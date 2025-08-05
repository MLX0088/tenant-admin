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
import com.tenant.business.domain.TbTenantNotice;
import com.tenant.business.service.ITbTenantNoticeService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 租户公告管理Controller
 * 
 * @author luanyu
 * @date 2025-05-08
 */
@Controller
@RequestMapping("/business/TenantNotice")
public class TbTenantNoticeController extends BaseController
{
    private String prefix = "business/TenantNotice";

    @Autowired
    private ITbTenantNoticeService tbTenantNoticeService;

    @RequiresPermissions("business:TenantNotice:view")
    @GetMapping()
    public String TenantNotice()
    {
        return prefix + "/TenantNotice";
    }

    @RequiresPermissions("business:TenantNotice:view")
    @GetMapping("/nav")
    public String TenantNoticeNav()
    {
        return prefix + "/TenantNoticeNav";
    }

    /**
     * 查询租户公告管理列表
     */
    @RequiresPermissions("business:TenantNotice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbTenantNotice tbTenantNotice)
    {
        startPage();
        List<TbTenantNotice> list = tbTenantNoticeService.selectTbTenantNoticeList(tbTenantNotice);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        TbTenantNotice tbTenantNotice = new TbTenantNotice();
        tbTenantNotice.setTenantId(getTenantId());
        List<TbTenantNotice> list = tbTenantNoticeService.selectTbTenantNoticeList(tbTenantNotice);
        return AjaxResult.success(list);
    }

    /**
     * 导出租户公告管理列表
     */
    @RequiresPermissions("business:TenantNotice:export")
    @Log(title = "租户公告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbTenantNotice tbTenantNotice)
    {
        List<TbTenantNotice> list = tbTenantNoticeService.selectTbTenantNoticeList(tbTenantNotice);
        ExcelUtil<TbTenantNotice> util = new ExcelUtil<TbTenantNotice>(TbTenantNotice.class);
        return util.exportExcel(list, "租户公告管理数据");
    }

    /**
     * 新增租户公告管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增租户公告管理
     */
    @GetMapping("/addGG")
    public String addGG()
    {
        return prefix + "/addGG";
    }
    @GetMapping("/addLB")
    public String addLB()
    {
        return prefix + "/addLB";
    }

    /**
     * 新增保存租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:add")
    @Log(title = "租户公告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbTenantNotice tbTenantNotice)
    {
        return toAjax(tbTenantNoticeService.insertTbTenantNotice(tbTenantNotice));
    }

    /**
     * 修改租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantNotice tbTenantNotice = tbTenantNoticeService.selectTbTenantNoticeById(id);
        mmap.put("tbTenantNotice", tbTenantNotice);
        return prefix + "/edit";
    }

    /**
     * 修改租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:edit")
    @GetMapping("/editNav/{id}")
    public String editNav(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantNotice tbTenantNotice = tbTenantNoticeService.selectTbTenantNoticeById(id);
        mmap.put("tbTenantNotice", tbTenantNotice);
        return prefix + "/editNav";
    }
    @RequiresPermissions("business:TenantNotice:edit")
    @GetMapping("/editGG/{id}")
    public String editGG(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantNotice tbTenantNotice = tbTenantNoticeService.selectTbTenantNoticeById(id);
        mmap.put("tbTenantNotice", tbTenantNotice);
        return prefix + "/editGG";
    }
    @RequiresPermissions("business:TenantNotice:edit")
    @GetMapping("/editLB/{id}")
    public String editLB(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantNotice tbTenantNotice = tbTenantNoticeService.selectTbTenantNoticeById(id);
        mmap.put("tbTenantNotice", tbTenantNotice);
        return prefix + "/editLB";
    }
    /**
     * 详情租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantNotice tbTenantNotice = tbTenantNoticeService.selectTbTenantNoticeById(id);
        return success(tbTenantNotice);
    }

    /**
     * 修改保存租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:edit")
    @Log(title = "租户公告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbTenantNotice tbTenantNotice)
    {
        return toAjax(tbTenantNoticeService.updateTbTenantNotice(tbTenantNotice));
    }

    /**
     * 删除租户公告管理
     */
    @RequiresPermissions("business:TenantNotice:remove")
    @Log(title = "租户公告管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbTenantNoticeService.deleteTbTenantNoticeByIds(ids));
    }
}
