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
import com.tenant.business.domain.TbRoomPush;
import com.tenant.business.service.ITbRoomPushService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 房间推送信息Controller
 * 
 * @author luanyu
 * @date 2025-05-06
 */
@Controller
@RequestMapping("/business/RoomPush")
public class TbRoomPushController extends BaseController
{
    private String prefix = "business/RoomPush";

    @Autowired
    private ITbRoomPushService tbRoomPushService;

    @RequiresPermissions("business:RoomPush:view")
    @GetMapping()
    public String RoomPush()
    {
        return prefix + "/RoomPush";
    }

    /**
     * 查询房间推送信息列表
     */
    @RequiresPermissions("business:RoomPush:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRoomPush tbRoomPush)
    {
        startPage();
        List<TbRoomPush> list = tbRoomPushService.selectTbRoomPushList(tbRoomPush);
        return getDataTable(list);
    }

    /**
     * 导出房间推送信息列表
     */
    @RequiresPermissions("business:RoomPush:export")
    @Log(title = "房间推送信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRoomPush tbRoomPush)
    {
        List<TbRoomPush> list = tbRoomPushService.selectTbRoomPushList(tbRoomPush);
        ExcelUtil<TbRoomPush> util = new ExcelUtil<TbRoomPush>(TbRoomPush.class);
        return util.exportExcel(list, "房间推送信息数据");
    }

    /**
     * 新增房间推送信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存房间推送信息
     */
    @RequiresPermissions("business:RoomPush:add")
    @Log(title = "房间推送信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRoomPush tbRoomPush)
    {
        return toAjax(tbRoomPushService.insertTbRoomPush(tbRoomPush));
    }

    /**
     * 修改房间推送信息
     */
    @RequiresPermissions("business:RoomPush:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRoomPush tbRoomPush = tbRoomPushService.selectTbRoomPushById(id);
        mmap.put("tbRoomPush", tbRoomPush);
        return prefix + "/edit";
    }
    /**
     * 详情房间推送信息
     */
    @RequiresPermissions("business:RoomPush:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRoomPush tbRoomPush = tbRoomPushService.selectTbRoomPushById(id);
        return success(tbRoomPush);
    }

    /**
     * 修改保存房间推送信息
     */
    @RequiresPermissions("business:RoomPush:edit")
    @Log(title = "房间推送信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRoomPush tbRoomPush)
    {
        return toAjax(tbRoomPushService.updateTbRoomPush(tbRoomPush));
    }

    /**
     * 删除房间推送信息
     */
    @RequiresPermissions("business:RoomPush:remove")
    @Log(title = "房间推送信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRoomPushService.deleteTbRoomPushByIds(ids));
    }
}
