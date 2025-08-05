package com.tenant.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.domain.TbTurntableRule;
import com.tenant.business.service.ITbTenantConfigService;
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
import com.tenant.business.domain.TbVipGrade;
import com.tenant.business.service.ITbVipGradeService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 会员等级管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/VipGrade")
public class TbVipGradeController extends BaseController
{
    private String prefix = "business/VipGrade";

    @Autowired
    private ITbVipGradeService tbVipGradeService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;

    @RequiresPermissions("business:VipGrade:view")
    @GetMapping()
    public String VipGrade()
    {
        return prefix + "/VipGrade";
    }

    /**
     * 查询会员等级管理列表
     */
    @RequiresPermissions("business:VipGrade:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbVipGrade tbVipGrade)
    {
        startPage();
        List<TbVipGrade> list = tbVipGradeService.selectTbVipGradeList(tbVipGrade);
        return getDataTable(list);
    }

    /**
     * 导出会员等级管理列表
     */
    @RequiresPermissions("business:VipGrade:export")
    @Log(title = "会员等级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbVipGrade tbVipGrade)
    {
        List<TbVipGrade> list = tbVipGradeService.selectTbVipGradeList(tbVipGrade);
        ExcelUtil<TbVipGrade> util = new ExcelUtil<TbVipGrade>(TbVipGrade.class);
        return util.exportExcel(list, "会员等级管理数据");
    }

    /**
     * 新增会员等级管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员等级管理
     */
    @RequiresPermissions("business:VipGrade:add")
    @Log(title = "会员等级管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbVipGrade tbVipGrade)
    {
        return toAjax(tbVipGradeService.insertTbVipGrade(tbVipGrade));
    }

    /**
     * 修改会员等级管理
     */
    @RequiresPermissions("business:VipGrade:edit")
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        TbVipGrade tbVipGrade = new TbVipGrade();
        tbVipGrade.setTenantId(getSelectDeptId());
        List<TbVipGrade> list = tbVipGradeService.selectTbVipGradeList(tbVipGrade);
        TbTenantConfig config =  tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
        mmap.put("list", list);
        mmap.put("vipGradeDesc", config.getVipGradeDesc());
        return prefix + "/edit";
    }
    /**
     * 详情会员等级管理
     */
    @RequiresPermissions("business:VipGrade:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbVipGrade tbVipGrade = tbVipGradeService.selectTbVipGradeById(id);
        return success(tbVipGrade);
    }

    public class DataModel{
        ArrayList<TbVipGrade> list = new ArrayList<>();
        String vipGradeDesc;

        public ArrayList<TbVipGrade> getList() {
            return list;
        }

        public void setList(ArrayList<TbVipGrade> list) {
            this.list = list;
        }

        public String getVipGradeDesc() {
            return vipGradeDesc;
        }

        public void setVipGradeDesc(String vipGradeDesc) {
            this.vipGradeDesc = vipGradeDesc;
        }
    }

    /**
     * 修改保存会员等级管理
     */
    @RequiresPermissions("business:VipGrade:edit")
    @Log(title = "会员等级管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DataModel dataModel)
    {
        int count = 0;
        for (TbVipGrade obj :dataModel.list) {
            count += tbVipGradeService.updateTbVipGrade(obj);
        }
        if(count>0){
            TbTenantConfig config =  tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
            config.setVipGradeDesc(dataModel.vipGradeDesc);
            tbTenantConfigService.updateTbTenantConfig(config);
        }
        return toAjax(count);
    }

    /**
     * 删除会员等级管理
     */
    @RequiresPermissions("business:VipGrade:remove")
    @Log(title = "会员等级管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbVipGradeService.deleteTbVipGradeByIds(ids));
    }
}
