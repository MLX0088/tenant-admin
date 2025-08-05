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
import com.tenant.business.domain.TbVipInfo;
import com.tenant.business.service.ITbVipInfoService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 会员信息Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/VipInfo")
public class TbVipInfoController extends BaseController
{
    private String prefix = "business/VipInfo";

    @Autowired
    private ITbVipInfoService tbVipInfoService;

    @RequiresPermissions("business:VipInfo:view")
    @GetMapping()
    public String VipInfo()
    {
        return prefix + "/VipInfo";
    }

    /**
     * 查询会员信息列表
     */
    @RequiresPermissions("business:VipInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbVipInfo tbVipInfo)
    {
        startPage();
        List<TbVipInfo> list = tbVipInfoService.selectTbVipInfoList(tbVipInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员信息列表
     */
    @RequiresPermissions("business:VipInfo:export")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbVipInfo tbVipInfo)
    {
        List<TbVipInfo> list = tbVipInfoService.selectTbVipInfoList(tbVipInfo);
        ExcelUtil<TbVipInfo> util = new ExcelUtil<TbVipInfo>(TbVipInfo.class);
        return util.exportExcel(list, "会员信息数据");
    }

    /**
     * 新增会员信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员信息
     */
    @RequiresPermissions("business:VipInfo:add")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbVipInfo tbVipInfo)
    {
        return toAjax(tbVipInfoService.insertTbVipInfo(tbVipInfo));
    }

    /**
     * 修改会员信息
     */
    @RequiresPermissions("business:VipInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbVipInfo tbVipInfo = tbVipInfoService.selectTbVipInfoById(id);
        mmap.put("tbVipInfo", tbVipInfo);
        return prefix + "/edit";
    }
    /**
     * 详情会员信息
     */
    @RequiresPermissions("business:VipInfo:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbVipInfo tbVipInfo = tbVipInfoService.selectTbVipInfoById(id);
        return success(tbVipInfo);
    }

    /**
     * 修改保存会员信息
     */
    @RequiresPermissions("business:VipInfo:edit")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbVipInfo tbVipInfo)
    {
        return toAjax(tbVipInfoService.updateTbVipInfo(tbVipInfo));
    }

    /**
     * 删除会员信息
     */
    @RequiresPermissions("business:VipInfo:remove")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbVipInfoService.deleteTbVipInfoByIds(ids));
    }
}
