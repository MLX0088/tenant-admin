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
import com.tenant.business.domain.TbAccountConfig;
import com.tenant.business.service.ITbAccountConfigService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 上/下分账号设置Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/AccountConfig")
public class TbAccountConfigController extends BaseController
{
    private String prefix = "business/AccountConfig";

    @Autowired
    private ITbAccountConfigService tbAccountConfigService;

    @RequiresPermissions("business:AccountConfig:view")
    @GetMapping()
    public String AccountConfig()
    {
        return prefix + "/AccountConfig";
    }

    /**
     * 查询上/下分账号设置列表
     */
    @RequiresPermissions("business:AccountConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbAccountConfig tbAccountConfig)
    {
        startPage();
        List<TbAccountConfig> list = tbAccountConfigService.selectTbAccountConfigList(tbAccountConfig);
        return getDataTable(list);
    }

    /**
     * 导出上/下分账号设置列表
     */
    @RequiresPermissions("business:AccountConfig:export")
    @Log(title = "上/下分账号设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbAccountConfig tbAccountConfig)
    {
        List<TbAccountConfig> list = tbAccountConfigService.selectTbAccountConfigList(tbAccountConfig);
        ExcelUtil<TbAccountConfig> util = new ExcelUtil<TbAccountConfig>(TbAccountConfig.class);
        return util.exportExcel(list, "上/下分账号设置数据");
    }

    /**
     * 新增上/下分账号设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存上/下分账号设置
     */
    @RequiresPermissions("business:AccountConfig:add")
    @Log(title = "上/下分账号设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbAccountConfig tbAccountConfig)
    {
        return toAjax(tbAccountConfigService.insertTbAccountConfig(tbAccountConfig));
    }

    /**
     * 修改上/下分账号设置
     */
    @RequiresPermissions("business:AccountConfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbAccountConfig tbAccountConfig = tbAccountConfigService.selectTbAccountConfigById(id);
        mmap.put("tbAccountConfig", tbAccountConfig);
        return prefix + "/edit";
    }
    /**
     * 详情上/下分账号设置
     */
    @RequiresPermissions("business:AccountConfig:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbAccountConfig tbAccountConfig = tbAccountConfigService.selectTbAccountConfigById(id);
        return success(tbAccountConfig);
    }

    /**
     * 修改保存上/下分账号设置
     */
    @RequiresPermissions("business:AccountConfig:edit")
    @Log(title = "上/下分账号设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbAccountConfig tbAccountConfig)
    {
        return toAjax(tbAccountConfigService.updateTbAccountConfig(tbAccountConfig));
    }

    /**
     * 删除上/下分账号设置
     */
    @RequiresPermissions("business:AccountConfig:remove")
    @Log(title = "上/下分账号设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbAccountConfigService.deleteTbAccountConfigByIds(ids));
    }
}
