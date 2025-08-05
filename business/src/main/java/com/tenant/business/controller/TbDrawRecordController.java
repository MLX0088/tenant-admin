package com.tenant.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.tenant.business.Global;
import com.tenant.business.domain.TbRoomConfig;
import com.tenant.business.domain.statistics.DrawWinRecord;
import com.tenant.business.domain.vo.DrawDashboardVo;
import com.tenant.business.service.ITbRoomConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbDrawRecord;
import com.tenant.business.service.ITbDrawRecordService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 开奖记录Controller
 * 
 * @author luanyu
 * @date 2025-05-04
 */
@Controller
@RequestMapping("/business/DrawRecord")
public class TbDrawRecordController extends BaseController
{
    private String prefix = "business/DrawRecord";

    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;

    @RequiresPermissions("business:DrawRecord:view")
    @GetMapping()
    public String DrawRecord()
    {
        return prefix + "/DrawRecord";
    }

    @RequiresPermissions("business:DrawWin:view")
    @GetMapping("/DrawWin")
    public String DrawWin()
    {
        return prefix + "/DrawWin";
    }

    /**
     * 查询开奖记录列表
     */
    @RequiresPermissions("business:DrawRecord:list")
    @PostMapping("/drawWinList")
    @ResponseBody
    public TableDataInfo drawWinList(TbDrawRecord tbDrawRecord)
    {
        startPage();
        List<DrawWinRecord> list = tbDrawRecordService.drawWinList(tbDrawRecord);
        return getDataTable(list);
    }

    /**
     * 查询开奖记录列表
     */
    @RequiresPermissions("business:DrawRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbDrawRecord tbDrawRecord)
    {
        startPage();
        List<TbDrawRecord> list = tbDrawRecordService.selectTbDrawRecordList(tbDrawRecord);
        return getDataTable(list);
    }

    @GetMapping("/list2")
    @ResponseBody
    public AjaxResult list2(TbDrawRecord tbDrawRecord)
    {
        startPage();
        tbDrawRecord.setTenantId(getTenantId());
        List<DrawDashboardVo> list = tbDrawRecordService.selectListForDashboard(tbDrawRecord);
        return success(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp(TbDrawRecord tbDrawRecord)
    {
        if(tbDrawRecord.getGameType()==0){
            if(Global.jndDrawList == null || Global.jndDrawList.size()<10){
                startPage();
                tbDrawRecord.setTenantId(getTenantId());
                List<TbDrawRecord> list = tbDrawRecordService.selectTbDrawRecordList(tbDrawRecord);
                Global.jndDrawList =list;
            }
            return success(Global.jndDrawList);

        }else if(tbDrawRecord.getGameType()==2){
            if(Global.twDrawList == null || Global.twDrawList.size()<10){
                startPage();
                tbDrawRecord.setTenantId(getTenantId());
                List<TbDrawRecord> list = tbDrawRecordService.selectTbDrawRecordList(tbDrawRecord);
                Global.twDrawList =list;
            }
            return success(Global.twDrawList);

        }

        return success(new ArrayList<>());
    }

    /**
     * 导出开奖记录列表
     */
    @RequiresPermissions("business:DrawRecord:export")
    @Log(title = "开奖记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbDrawRecord tbDrawRecord)
    {
        List<TbDrawRecord> list = tbDrawRecordService.selectTbDrawRecordList(tbDrawRecord);
        ExcelUtil<TbDrawRecord> util = new ExcelUtil<TbDrawRecord>(TbDrawRecord.class);
        return util.exportExcel(list, "开奖记录数据");
    }

    /**
     * 新增开奖记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存开奖记录
     */
    @RequiresPermissions("business:DrawRecord:add")
    @Log(title = "开奖记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbDrawRecord tbDrawRecord)
    {
        return toAjax(tbDrawRecordService.insertTbDrawRecord(tbDrawRecord));
    }

    /**
     * 修改开奖记录
     */
    @RequiresPermissions("business:DrawRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbDrawRecord tbDrawRecord = tbDrawRecordService.selectTbDrawRecordById(id);
        mmap.put("tbDrawRecord", tbDrawRecord);
        return prefix + "/edit";
    }
    /**
     * 详情开奖记录
     */
    @RequiresPermissions("business:DrawRecord:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbDrawRecord tbDrawRecord = tbDrawRecordService.selectTbDrawRecordById(id);
        return success(tbDrawRecord);
    }

    /**
     * 详情开奖记录
     */
    @GetMapping("/v1/currentDrawRecord")
    @ResponseBody
    public AjaxResult currentDrawRecord(TbDrawRecord temp)
    {
        if(temp.getRoomConfigId() == null || temp.getRoomConfigId().longValue()==0){
            return error("RoomConfigId 缺失");
        }
        TbRoomConfig config = tbRoomConfigService.selectTbRoomConfigById(temp.getRoomConfigId());
        if(config.getRoomName().contains("加拿大")){
            return success(Global.jndCurrentDraw);
        }else{
            return success(Global.twCurrentDraw);
        }
    }

    /**
     * 修改保存开奖记录
     */
    @RequiresPermissions("business:DrawRecord:edit")
    @Log(title = "开奖记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbDrawRecord tbDrawRecord)
    {
        return toAjax(tbDrawRecordService.updateTbDrawRecord(tbDrawRecord));
    }

    /**
     * 删除开奖记录
     */
    @RequiresPermissions("business:DrawRecord:remove")
    @Log(title = "开奖记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbDrawRecordService.deleteTbDrawRecordByIds(ids));
    }
}
