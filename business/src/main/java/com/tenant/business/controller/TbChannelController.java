package com.tenant.business.controller;

import java.util.List;

import com.tenant.business.domain.statistics.ChannelRecord;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.system.service.ISysUserService;
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
import com.tenant.business.domain.TbChannel;
import com.tenant.business.service.ITbChannelService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 渠道来源Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/Channel")
public class TbChannelController extends BaseController
{
    private String prefix = "business/Channel";

    @Autowired
    private ITbChannelService tbChannelService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("business:Channel:view")
    @GetMapping()
    public String Channel()
    {
        return prefix + "/Channel";
    }

    /**
     * 查询渠道来源列表
     */
    @RequiresPermissions("business:Channel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbChannel tbChannel)
    {
        startPage();
        List<ChannelRecord> list = tbChannelService.selectChannelList(tbChannel);
        return getDataTable(list);
    }

    /**
     * 导出渠道来源列表
     */
    @RequiresPermissions("business:Channel:export")
    @Log(title = "渠道来源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbChannel tbChannel)
    {
        List<TbChannel> list = tbChannelService.selectTbChannelList(tbChannel);
        ExcelUtil<TbChannel> util = new ExcelUtil<TbChannel>(TbChannel.class);
        return util.exportExcel(list, "渠道来源数据");
    }

    /**
     * 新增渠道来源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存渠道来源
     */
    @RequiresPermissions("business:Channel:add")
    @Log(title = "渠道来源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbChannel tbChannel)
    {
        return toAjax(tbChannelService.insertTbChannel(tbChannel));
    }

    @Log(title = "渠道来源", businessType = BusinessType.INSERT)
    @PostMapping("/v1/add")
    @ResponseBody
    public AjaxResult addSaveForApp(TbChannel tbChannel)
    {
        tbChannel.setTenantId(getTenantId());
        int  insert = tbChannelService.insertTbChannel(tbChannel);
        SysUser user = new SysUser(ShiroUtils.getUserId());
        user.setChannalId(tbChannel.getId());
        sysUserService.updateUser(user);
        return AjaxResult.success(tbChannel.getId().intValue());
    }

    /**
     * 修改渠道来源
     */
    @RequiresPermissions("business:Channel:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbChannel tbChannel = tbChannelService.selectTbChannelById(id);
        mmap.put("tbChannel", tbChannel);
        return prefix + "/edit";
    }
    /**
     * 详情渠道来源
     */
    @RequiresPermissions("business:Channel:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbChannel tbChannel = tbChannelService.selectTbChannelById(id);
        return success(tbChannel);
    }

    /**
     * 修改保存渠道来源
     */
    @RequiresPermissions("business:Channel:edit")
    @Log(title = "渠道来源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbChannel tbChannel)
    {
        return toAjax(tbChannelService.updateTbChannel(tbChannel));
    }

    /**
     * 删除渠道来源
     */
    @RequiresPermissions("business:Channel:remove")
    @Log(title = "渠道来源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbChannelService.deleteTbChannelByIds(ids));
    }
}
