package com.tenant.business.controller;

import java.util.List;

import com.tenant.business.service.ITbImagesService;
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
import com.tenant.business.domain.TbAvatar;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 头像管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/Avatar")
public class TbAvatarController extends BaseController
{
    private String prefix = "business/Avatar";

    @Autowired
    private ITbAvatarService tbAvatarService;
    @Autowired
    private ITbImagesService tbImagesService;

    @RequiresPermissions("business:Avatar:view")
    @GetMapping()
    public String Avatar()
    {
        return prefix + "/Avatar";
    }

    /**
     * 查询头像管理列表
     */
    @RequiresPermissions("business:Avatar:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbAvatar tbAvatar)
    {
        startPage();
        List<TbAvatar> list = tbAvatarService.selectTbAvatarList(tbAvatar);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult list()
    {
        TbAvatar tbAvatar = new TbAvatar();
        tbAvatar.setTenantId(getTenantId());
        List<TbAvatar> list = tbAvatarService.selectTbAvatarList(tbAvatar);
        return success(list);
    }

    /**
     * 导出头像管理列表
     */
    @RequiresPermissions("business:Avatar:export")
    @Log(title = "头像管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbAvatar tbAvatar)
    {
        List<TbAvatar> list = tbAvatarService.selectTbAvatarList(tbAvatar);
        ExcelUtil<TbAvatar> util = new ExcelUtil<TbAvatar>(TbAvatar.class);
        return util.exportExcel(list, "头像管理数据");
    }

    /**
     * 新增头像管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存头像管理
     */
    @RequiresPermissions("business:Avatar:add")
    @Log(title = "头像管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbAvatar tbAvatar)
    {
        return toAjax(tbAvatarService.insertTbAvatar(tbAvatar));
    }

    /**
     * 修改头像管理
     */
    @RequiresPermissions("business:Avatar:edit")
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        TbAvatar param = new TbAvatar();
        param.setTenantId(getTenantId());
        List<TbAvatar> avatarList = tbAvatarService.selectTbAvatarList(param);
        mmap.put("avatarList", avatarList);
        return prefix + "/edit";
    }
    /**
     * 详情头像管理
     */
    @RequiresPermissions("business:Avatar:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbAvatar tbAvatar = tbAvatarService.selectTbAvatarById(id);
        return success(tbAvatar);
    }

    /**
     * 修改保存头像管理
     */
    @RequiresPermissions("business:Avatar:edit")
    @Log(title = "头像管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbAvatar tbAvatar)
    {
        return toAjax(tbAvatarService.updateTbAvatar(tbAvatar));
    }

    /**
     * 删除头像管理
     */
    @RequiresPermissions("business:Avatar:remove")
    @Log(title = "头像管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        long id = Long.parseLong(ids);
        TbAvatar tbAvatar = tbAvatarService.selectTbAvatarById(id);
        int  deleteResult= tbAvatarService.deleteTbAvatarByIds(ids);
        if(deleteResult>0){
            TbAvatar param = new TbAvatar();
            param.setHeadImagesId(tbAvatar.getHeadImagesId());
            List<TbAvatar> list = tbAvatarService.selectTbAvatarList(param);
            if(list == null || list.size()==0){
                tbImagesService.deleteTbImagesById(tbAvatar.getHeadImagesId());
            }
        }

        return toAjax(deleteResult);
    }
}
