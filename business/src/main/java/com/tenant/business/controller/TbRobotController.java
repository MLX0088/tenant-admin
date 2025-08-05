package com.tenant.business.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.tenant.business.Global;
import com.tenant.business.domain.TbAvatar;
import com.tenant.business.domain.form.RebotBatchForm;
import com.tenant.business.domain.form.RebotGapForm;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.domain.SysConfig;
import com.tenant.system.service.ISysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbRobot;
import com.tenant.business.service.ITbRobotService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 机器人管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/Robot")
public class TbRobotController extends BaseController
{
    private String prefix = "business/Robot";

    @Autowired
    private ITbRobotService tbRobotService;
    @Autowired
    private ITbAvatarService tbAvatarService;
    @Autowired
    ISysConfigService sysConfigService;

    @RequiresPermissions("business:Robot:view")
    @GetMapping()
    public String Robot(Model model)
    {
        String key = "rebot.gap."+getTenantId();
        SysConfig config = sysConfigService.getConfigByKey(key);
        if(config == null){
            model.addAttribute("gap", Global.robotAddScoreGap);
        }else{
            model.addAttribute("gap", config.getConfigValue());
        }

        return prefix + "/Robot";
    }

    /**
     * 查询机器人管理列表
     */
    @RequiresPermissions("business:Robot:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbRobot tbRobot)
    {
        startPage();
        List<TbRobot> list = tbRobotService.selectTbRobotList(tbRobot);
        return getDataTable(list);
    }

    /**
     * 导出机器人管理列表
     */
    @RequiresPermissions("business:Robot:export")
    @Log(title = "机器人管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbRobot tbRobot)
    {
        List<TbRobot> list = tbRobotService.selectTbRobotList(tbRobot);
        ExcelUtil<TbRobot> util = new ExcelUtil<TbRobot>(TbRobot.class);
        return util.exportExcel(list, "机器人管理数据");
    }

    /**
     * 新增机器人管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机器人管理
     */
    @RequiresPermissions("business:Robot:add")
    @Log(title = "机器人管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbRobot tbRobot)
    {
        TbAvatar param = new TbAvatar();
        param.setTenantId(tbRobot.getTenantId());
        List<TbAvatar> avatars = tbAvatarService.selectTbAvatarList(param);
        if(avatars.size()>0){
            tbRobot.setAvatarId(avatars.get(new Random().nextInt(avatars.size())).getHeadImagesId());
        }
        return toAjax(tbRobotService.insertTbRobot(tbRobot));
    }

    @RequiresPermissions("business:Robot:add")
    @Log(title = "机器人管理", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    @ResponseBody
    public AjaxResult addBatch(RebotBatchForm form)
    {
        String[] rooms = null;
        if(!StringUtils.isEmpty(form.getRoomName())){
            rooms = new String[]{form.getRoomName()};
        }
        tbRobotService.createRebotForTenantId(getTenantId(),form.getNum(),rooms);
        return success();
    }

    @PostMapping("/changeGap")
    @ResponseBody
    public AjaxResult changeGap(RebotGapForm form)
    {
        String key = "rebot.gap."+getTenantId();
        SysConfig config = sysConfigService.getConfigByKey(key);
        if(config == null){
            config = new SysConfig();
            config.setConfigKey(key);
            config.setConfigValue(form.getMin()+"");
            config.setConfigName("机器人上分间隔小时数");
            config.setConfigType("N");
            config.setCreateBy(ShiroUtils.getLoginName());
            config.setCreateTime(new Date());
            sysConfigService.insertConfig(config);
        }else{
            config.setConfigValue(form.getMin()+"");
            sysConfigService.updateConfig(config);
        }
        return success();
    }

    @GetMapping("/createRebotForTenantId")
    @ResponseBody
    public AjaxResult createRebotForTenantId(Long id, int count,String roomName)
    {
        String[] rooms = null;
        if(!StringUtils.isEmpty(roomName)){
            rooms = new String[]{roomName};
        }
        tbRobotService.createRebotForTenantId(id,count,rooms);
        return success();
    }


    /**
     * 修改机器人管理
     */
    @RequiresPermissions("business:Robot:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRobot tbRobot = tbRobotService.selectTbRobotById(id);
        mmap.put("tbRobot", tbRobot);
        return prefix + "/edit";
    }
    /**
     * 详情机器人管理
     */
    @RequiresPermissions("business:Robot:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbRobot tbRobot = tbRobotService.selectTbRobotById(id);
        return success(tbRobot);
    }

    /**
     * 修改保存机器人管理
     */
    @RequiresPermissions("business:Robot:edit")
    @Log(title = "机器人管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbRobot tbRobot)
    {
        return toAjax(tbRobotService.updateTbRobot(tbRobot));
    }

    /**
     * 删除机器人管理
     */
    @RequiresPermissions("business:Robot:remove")
    @Log(title = "机器人管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbRobotService.deleteTbRobotByIds(ids));
    }
}
