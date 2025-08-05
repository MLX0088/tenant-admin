package com.tenant.business.controller;

import java.util.List;

import com.tenant.business.Global;
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
import com.tenant.business.domain.TbGameCover;
import com.tenant.business.service.ITbGameCoverService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 游戏类型封面Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/GameCover")
public class TbGameCoverController extends BaseController
{
    private String prefix = "business/GameCover";

    @Autowired
    private ITbGameCoverService tbGameCoverService;

    @RequiresPermissions("business:GameCover:view")
    @GetMapping()
    public String GameCover()
    {
        return prefix + "/GameCover";
    }

    /**
     * 查询游戏类型封面列表
     */
    @RequiresPermissions("business:GameCover:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbGameCover tbGameCover)
    {
        startPage();
        List<TbGameCover> list = tbGameCoverService.selectTbGameCoverList(tbGameCover);
        return getDataTable(list);
    }

    /**
     * 查询游戏类型封面列表
     */
    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        startPage();
        TbGameCover param = new TbGameCover();
        param.setTenantId(getTenantId());
        List<TbGameCover> tbGameCovers = tbGameCoverService.selectTbGameCoverList(param);
        return AjaxResult.success(tbGameCovers);
    }

    /**
     * 导出游戏类型封面列表
     */
    @RequiresPermissions("business:GameCover:export")
    @Log(title = "游戏类型封面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbGameCover tbGameCover)
    {
        List<TbGameCover> list = tbGameCoverService.selectTbGameCoverList(tbGameCover);
        ExcelUtil<TbGameCover> util = new ExcelUtil<TbGameCover>(TbGameCover.class);
        return util.exportExcel(list, "游戏类型封面数据");
    }

    /**
     * 新增游戏类型封面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存游戏类型封面
     */
    @RequiresPermissions("business:GameCover:add")
    @Log(title = "游戏类型封面", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbGameCover tbGameCover)
    {
        return toAjax(tbGameCoverService.insertTbGameCover(tbGameCover));
    }

    /**
     * 修改游戏类型封面
     */
    @RequiresPermissions("business:GameCover:edit")
    @GetMapping("/edit")
    public String edit( ModelMap mmap)
    {
        long tenantId = getSelectDeptId();
        TbGameCover param = new TbGameCover();
        param.setTenantId(tenantId);
        List<TbGameCover> tbGameCovers = tbGameCoverService.selectTbGameCoverList(param);
        for (TbGameCover obj:tbGameCovers ) {
            switch (obj.getType()){
                case "加拿大" : mmap.put("tbGameCoverJnd", obj); break;
                case "台湾" : mmap.put("tbGameCoverTw", obj); break;
            }
        }

        return prefix + "/edit";
    }
    /**
     * 详情游戏类型封面
     */
    @RequiresPermissions("business:GameCover:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbGameCover tbGameCover = tbGameCoverService.selectTbGameCoverById(id);
        return success(tbGameCover);
    }

    /**
     * 修改保存游戏类型封面
     */
    @RequiresPermissions("business:GameCover:edit")
    @Log(title = "游戏类型封面", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbGameCover tbGameCover)
    {
        return toAjax(tbGameCoverService.updateTbGameCover(tbGameCover));
    }

    /**
     * 删除游戏类型封面
     */
    @RequiresPermissions("business:GameCover:remove")
    @Log(title = "游戏类型封面", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbGameCoverService.deleteTbGameCoverByIds(ids));
    }
}
