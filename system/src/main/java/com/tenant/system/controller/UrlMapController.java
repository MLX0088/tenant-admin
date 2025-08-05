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
import com.tenant.system.domain.UrlMap;
import com.tenant.system.service.IUrlMapService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 短链Controller
 * 
 * @author 栾钰
 * @date 2023-03-07
 */
@Controller
@RequestMapping("/common/urlmap")
public class UrlMapController extends BaseController
{
    private String prefix = "common/urlmap";

    @Autowired
    private IUrlMapService urlMapService;

    @RequiresPermissions("common:urlmap:view")
    @GetMapping()
    public String urlmap()
    {
        return prefix + "/urlmap";
    }

    /**
     * 查询短链列表
     */
    @RequiresPermissions("common:urlmap:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UrlMap urlMap)
    {
        startPage();
        List<UrlMap> list = urlMapService.selectUrlMapList(urlMap);
        return getDataTable(list);
    }

    /**
     * 导出短链列表
     */
    @RequiresPermissions("common:urlmap:export")
    @Log(title = "短链", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UrlMap urlMap)
    {
        List<UrlMap> list = urlMapService.selectUrlMapList(urlMap);
        ExcelUtil<UrlMap> util = new ExcelUtil<UrlMap>(UrlMap.class);
        return util.exportExcel(list, "短链数据");
    }

    /**
     * 新增短链
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短链
     */
    @RequiresPermissions("common:urlmap:add")
    @Log(title = "短链", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UrlMap urlMap)
    {
        return toAjax(urlMapService.insertUrlMap(urlMap));
    }

    /**
     * 修改短链
     */
    @RequiresPermissions("common:urlmap:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UrlMap urlMap = urlMapService.selectUrlMapById(id);
        mmap.put("urlMap", urlMap);
        return prefix + "/edit";
    }
    /**
     * 详情短链
     */
    @RequiresPermissions("common:urlmap:edit")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        UrlMap urlMap = urlMapService.selectUrlMapById(id);
        return success(urlMap);
    }

    /**
     * 修改保存短链
     */
    @RequiresPermissions("common:urlmap:edit")
    @Log(title = "短链", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UrlMap urlMap)
    {
        return toAjax(urlMapService.updateUrlMap(urlMap));
    }

    /**
     * 删除短链
     */
    @RequiresPermissions("common:urlmap:remove")
    @Log(title = "短链", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(urlMapService.deleteUrlMapByIds(ids));
    }
}
