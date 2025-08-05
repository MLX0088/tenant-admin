package com.tenant.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbGameRoomCover;
import com.tenant.business.domain.TbRoomPush;
import com.tenant.business.domain.vo.KValue;
import com.tenant.business.service.ITbGameRoomCoverService;
import com.tenant.business.service.ITbRoomPushService;
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
import com.tenant.business.domain.TbRoomConfig;
import com.tenant.business.service.ITbRoomConfigService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 房间Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/RoomConfig")
public class TbRoomConfigController extends BaseController
{
    private String prefix = "business/RoomConfig";

    @Autowired
    private ITbRoomConfigService tbRoomConfigService;

    @Autowired
    private ITbRoomPushService tbRoomPushService;

    @Autowired
    private ITbGameRoomCoverService tbGameRoomCoverService;

    @RequiresPermissions("business:RoomConfig:view")
    @GetMapping()
    public String RoomConfig()
    {
        return prefix + "/RoomConfig";
    }

    /**
     * 查询房间列表
     */
    @RequiresPermissions("business:RoomConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRoomConfig tbRoomConfig)
    {
        startPage();
        List<TbRoomConfig> list = tbRoomConfigService.selectTbRoomConfigList(tbRoomConfig);
        return getDataTable(list);
    }

    /**
     * 导出房间列表
     */
    @RequiresPermissions("business:RoomConfig:export")
    @Log(title = "房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRoomConfig tbRoomConfig)
    {
        List<TbRoomConfig> list = tbRoomConfigService.selectTbRoomConfigList(tbRoomConfig);
        ExcelUtil<TbRoomConfig> util = new ExcelUtil<TbRoomConfig>(TbRoomConfig.class);
        return util.exportExcel(list, "房间数据");
    }

    /**
     * 新增房间
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存房间
     */
    @RequiresPermissions("business:RoomConfig:add")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRoomConfig tbRoomConfig)
    {
        return toAjax(tbRoomConfigService.insertTbRoomConfig(tbRoomConfig));
    }

    /**
     * 修改房间
     */
    @RequiresPermissions("business:RoomConfig:edit")
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        long tenantId = getSelectDeptId();
        TbRoomConfig param = new TbRoomConfig();
        param.setTenantId(tenantId);
        List<TbRoomConfig> list = tbRoomConfigService.selectTbRoomConfigList(param);
        for (TbRoomConfig config:list) {
            switch (config.getRoomName()) {
                case "加拿大2.0" : mmap.put("tbRoomConfigJnd20", config); break;
                case "加拿大2.8" : mmap.put("tbRoomConfigJnd28", config); break;
                case "加拿大3.0" : mmap.put("tbRoomConfigJnd30", config); break;
                case "加拿大4.0" : mmap.put("tbRoomConfigJnd40", config); break;
                case "台湾2.0" : mmap.put("tbRoomConfigTw20", config); break;
                case "台湾2.8" : mmap.put("tbRoomConfigTw28", config); break;
                case "台湾3.0" : mmap.put("tbRoomConfigTw30", config); break;
                case "台湾4.0" : mmap.put("tbRoomConfigTw40", config); break;
            }
        }
        return prefix + "/edit";
    }
    /**
     * 详情房间
     */
    @RequiresPermissions("business:RoomConfig:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRoomConfig tbRoomConfig = tbRoomConfigService.selectTbRoomConfigById(id);
        return success(tbRoomConfig);
    }

    @GetMapping("/v1/getByCoverId")
    @ResponseBody
    public AjaxResult info(Long id)
    {
        TbGameRoomCover cover = tbGameRoomCoverService.selectTbGameRoomCoverById(id);
        if(cover!=null){
            TbRoomConfig tbRoomConfig = new TbRoomConfig();
            tbRoomConfig.setTenantId(0l);
            tbRoomConfig.setRoomName(cover.getType());
            List<TbRoomConfig> list = tbRoomConfigService.selectTbRoomConfigList(tbRoomConfig);
            if(list!=null  && list.size()>0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",list.get(0).getId());
                map.put("roomName",list.get(0).getRoomName());
                map.put("maxScore",list.get(0).getMaxScore());
                map.put("closeSecond",list.get(0).getCloseSecond());
                map.put("isOpen",list.get(0).getIsOpen());
                map.put("hideRoom",list.get(0).getHideRoom());
                map.put("isBackPressure",list.get(0).getIsBackPressure());
                map.put("isTailCode",list.get(0).getIsTailCode());
                map.put("isRobot",list.get(0).getIsRobot());
                map.put("isBanned",list.get(0).getIsBanned());
                map.put("rateRule",list.get(0).getRateRule());
                return success(list.get(0));
            }
            return error(null);
        }
        return error(null);
    }

    /**
     * 修改保存房间
     */
    @RequiresPermissions("business:RoomConfig:edit")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRoomConfig tbRoomConfig)
    {

        int result = tbRoomConfigService.updateTbRoomConfig(tbRoomConfig);
        if(result>0){
            tbRoomPushService.deleteTbRoomPushByTenantIdAndRoomName(tbRoomConfig.getTenantId(),tbRoomConfig.getRoomName());
            for (TbRoomPush push:tbRoomConfig.getPushList() ) {
                tbRoomPushService.insertTbRoomPush(push);
            }
        }
        return toAjax(result);
    }

    /**
     * 删除房间
     */
    @RequiresPermissions("business:RoomConfig:remove")
    @Log(title = "房间", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRoomConfigService.deleteTbRoomConfigByIds(ids));
    }
}
