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
import com.tenant.business.domain.TbRoomOdds;
import com.tenant.business.service.ITbRoomOddsService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 房间赔率Controller
 * 
 * @author luanyu
 * @date 2025-05-06
 */
@Controller
@RequestMapping("/business/RoomOdds")
public class TbRoomOddsController extends BaseController
{
    private String prefix = "business/RoomOdds";

    @Autowired
    private ITbRoomOddsService tbRoomOddsService;

    @RequiresPermissions("business:RoomOdds:view")
    @GetMapping()
    public String RoomOdds()
    {
        return prefix + "/RoomOdds";
    }

    /**
     * 查询房间赔率列表
     */
    @RequiresPermissions("business:RoomOdds:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRoomOdds tbRoomOdds)
    {
        startPage();
        List<TbRoomOdds> list = tbRoomOddsService.selectTbRoomOddsList(tbRoomOdds);
        return getDataTable(list);
    }

    /**
     * 导出房间赔率列表
     */
    @RequiresPermissions("business:RoomOdds:export")
    @Log(title = "房间赔率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRoomOdds tbRoomOdds)
    {
        List<TbRoomOdds> list = tbRoomOddsService.selectTbRoomOddsList(tbRoomOdds);
        ExcelUtil<TbRoomOdds> util = new ExcelUtil<TbRoomOdds>(TbRoomOdds.class);
        return util.exportExcel(list, "房间赔率数据");
    }

    /**
     * 新增房间赔率
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存房间赔率
     */
    @RequiresPermissions("business:RoomOdds:add")
    @Log(title = "房间赔率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRoomOdds tbRoomOdds)
    {
        return toAjax(tbRoomOddsService.insertTbRoomOdds(tbRoomOdds));
    }

    /**
     * 修改房间赔率
     */
    @RequiresPermissions("business:RoomOdds:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRoomOdds tbRoomOdds = tbRoomOddsService.selectTbRoomOddsById(id);
        mmap.put("tbRoomOdds", tbRoomOdds);
        return prefix + "/edit";
    }
    /**
     * 详情房间赔率
     */
    @RequiresPermissions("business:RoomOdds:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRoomOdds tbRoomOdds = tbRoomOddsService.selectTbRoomOddsById(id);
        return success(tbRoomOdds);
    }

    /**
     * 修改保存房间赔率
     */
    @RequiresPermissions("business:RoomOdds:edit")
    @Log(title = "房间赔率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRoomOdds tbRoomOdds)
    {
        return toAjax(tbRoomOddsService.updateTbRoomOdds(tbRoomOdds));
    }

    /**
     * 删除房间赔率
     */
    @RequiresPermissions("business:RoomOdds:remove")
    @Log(title = "房间赔率", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRoomOddsService.deleteTbRoomOddsByIds(ids));
    }
}
