package com.tenant.business.controller;

import java.util.List;

import com.tenant.business.domain.TbRoomConfig;
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
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 租客基础设置Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/TenantConfig")
public class TbTenantConfigController extends BaseController
{
    private String prefix = "business/TenantConfig";

    @Autowired
    private ITbTenantConfigService tbTenantConfigService;

    @RequiresPermissions("business:TenantConfig:view")
    @GetMapping()
    public String TenantConfig()
    {
        return prefix + "/TenantConfig";
    }

    /**
     * 查询租客基础设置列表
     */
    @RequiresPermissions("business:TenantConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbTenantConfig tbTenantConfig)
    {
        startPage();
        List<TbTenantConfig> list = tbTenantConfigService.selectTbTenantConfigList(tbTenantConfig);
        return getDataTable(list);
    }

    /**
     * 导出租客基础设置列表
     */
    @RequiresPermissions("business:TenantConfig:export")
    @Log(title = "租客基础设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbTenantConfig tbTenantConfig)
    {
        List<TbTenantConfig> list = tbTenantConfigService.selectTbTenantConfigList(tbTenantConfig);
        ExcelUtil<TbTenantConfig> util = new ExcelUtil<TbTenantConfig>(TbTenantConfig.class);
        return util.exportExcel(list, "租客基础设置数据");
    }

    /**
     * 新增租客基础设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存租客基础设置
     */
    @RequiresPermissions("business:TenantConfig:add")
    @Log(title = "租客基础设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbTenantConfig tbTenantConfig)
    {
        return toAjax(tbTenantConfigService.insertTbTenantConfig(tbTenantConfig));
    }

    /**
     * 修改租客基础设置
     */
    @RequiresPermissions("business:TenantConfig:edit")
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {

        long tenantId = getSelectDeptId();
        TbTenantConfig tbTenantConfig = tbTenantConfigService.selectTbTenantConfigByTenantId(tenantId);
        mmap.put("tbTenantConfig", tbTenantConfig);
        return prefix + "/edit";
    }
    /**
     * 详情租客基础设置
     */
    @RequiresPermissions("business:TenantConfig:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTenantConfig tbTenantConfig = tbTenantConfigService.selectTbTenantConfigById(id);
        return success(tbTenantConfig);
    }

    @GetMapping("/v1/info")
    @ResponseBody
    public AjaxResult info()
    {
        TbTenantConfig tbTenantConfig = tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
        return success(tbTenantConfig);
    }

    /**
     * 修改保存租客基础设置
     */
    @RequiresPermissions("business:TenantConfig:edit")
    @Log(title = "租客基础设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbTenantConfig tbTenantConfig)
    {
        return toAjax(tbTenantConfigService.updateTbTenantConfig(tbTenantConfig));
    }

    /**
     * 删除租客基础设置
     */
    @RequiresPermissions("business:TenantConfig:remove")
    @Log(title = "租客基础设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbTenantConfigService.deleteTbTenantConfigByIds(ids));
    }
}
