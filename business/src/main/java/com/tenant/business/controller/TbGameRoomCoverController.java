package com.tenant.business.controller;

import java.util.List;

import com.tenant.business.Global;
import com.tenant.business.domain.TbGameCover;
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
import com.tenant.business.domain.TbGameRoomCover;
import com.tenant.business.service.ITbGameRoomCoverService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 游戏房间封面Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/GameRoomCover")
public class TbGameRoomCoverController extends BaseController
{
    private String prefix = "business/GameRoomCover";

    @Autowired
    private ITbGameRoomCoverService tbGameRoomCoverService;

    @RequiresPermissions("business:GameRoomCover:view")
    @GetMapping()
    public String GameRoomCover()
    {
        return prefix + "/GameRoomCover";
    }

    /**
     * 查询游戏房间封面列表
     */
    @RequiresPermissions("business:GameRoomCover:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbGameRoomCover tbGameRoomCover)
    {
        startPage();
        List<TbGameRoomCover> list = tbGameRoomCoverService.selectTbGameRoomCoverList(tbGameRoomCover);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        long tenantId = getTenantId();
        TbGameRoomCover param = new TbGameRoomCover();
        param.setTenantId(tenantId);
        List<TbGameRoomCover> list = tbGameRoomCoverService.selectTbGameRoomCoverList(param);

        for (TbGameRoomCover obj:list) {
            if(obj.getType().contains("加拿大")){
                obj.setMaintain(Global.jndIsMaintain);
            }
            if(obj.getType().contains("台湾")){
                obj.setMaintain(Global.twIsMaintain);
            }
        }
        return AjaxResult.success(list);
    }
    /**
     * 导出游戏房间封面列表
     */
    @RequiresPermissions("business:GameRoomCover:export")
    @Log(title = "游戏房间封面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbGameRoomCover tbGameRoomCover)
    {
        List<TbGameRoomCover> list = tbGameRoomCoverService.selectTbGameRoomCoverList(tbGameRoomCover);
        ExcelUtil<TbGameRoomCover> util = new ExcelUtil<TbGameRoomCover>(TbGameRoomCover.class);
        return util.exportExcel(list, "游戏房间封面数据");
    }

    /**
     * 新增游戏房间封面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存游戏房间封面
     */
    @RequiresPermissions("business:GameRoomCover:add")
    @Log(title = "游戏房间封面", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbGameRoomCover tbGameRoomCover)
    {
        return toAjax(tbGameRoomCoverService.insertTbGameRoomCover(tbGameRoomCover));
    }

    /**
     * 修改游戏房间封面
     */
    @RequiresPermissions("business:GameRoomCover:edit")
    @GetMapping("/edit")
    public String edit( ModelMap mmap)
    {
        long tenantId = getSelectDeptId();
        TbGameRoomCover param = new TbGameRoomCover();
        param.setTenantId(tenantId);
        List<TbGameRoomCover> list = tbGameRoomCoverService.selectTbGameRoomCoverList(param);
        for (TbGameRoomCover obj:list ) {
            switch (obj.getType()){
                case "加拿大2.0" : mmap.put("tbGameRoomCoverJnd20", obj); break;
                case "加拿大2.8" : mmap.put("tbGameRoomCoverJnd28", obj); break;
                case "加拿大3.0" : mmap.put("tbGameRoomCoverJnd30", obj); break;
                case "加拿大4.0" : mmap.put("tbGameRoomCoverJnd40", obj); break;
                case "台湾2.0" : mmap.put("tbGameRoomCoverTw20", obj); break;
                case "台湾2.8" : mmap.put("tbGameRoomCoverTw28", obj); break;
                case "台湾3.0" : mmap.put("tbGameRoomCoverTw30", obj); break;
                case "台湾4.0" : mmap.put("tbGameRoomCoverTw40", obj); break;
            }
        }
        return prefix + "/edit";
    }
    /**
     * 详情游戏房间封面
     */
    @RequiresPermissions("business:GameRoomCover:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbGameRoomCover tbGameRoomCover = tbGameRoomCoverService.selectTbGameRoomCoverById(id);
        return success(tbGameRoomCover);
    }

    /**
     * 修改保存游戏房间封面
     */
    @RequiresPermissions("business:GameRoomCover:edit")
    @Log(title = "游戏房间封面", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbGameRoomCover tbGameRoomCover)
    {
        return toAjax(tbGameRoomCoverService.updateTbGameRoomCover(tbGameRoomCover));
    }

    /**
     * 删除游戏房间封面
     */
    @RequiresPermissions("business:GameRoomCover:remove")
    @Log(title = "游戏房间封面", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbGameRoomCoverService.deleteTbGameRoomCoverByIds(ids));
    }
}
