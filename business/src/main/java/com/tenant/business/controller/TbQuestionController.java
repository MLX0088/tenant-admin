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
import com.tenant.business.domain.TbQuestion;
import com.tenant.business.service.ITbQuestionService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 问题管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/Question")
public class TbQuestionController extends BaseController
{
    private String prefix = "business/Question";

    @Autowired
    private ITbQuestionService tbQuestionService;

    @RequiresPermissions("business:Question:view")
    @GetMapping()
    public String Question()
    {
        return prefix + "/Question";
    }

    /**
     * 查询问题管理列表
     */
    @RequiresPermissions("business:Question:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbQuestion tbQuestion)
    {
        startPage();
        List<TbQuestion> list = tbQuestionService.selectTbQuestionList(tbQuestion);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        TbQuestion tbQuestion = new TbQuestion();
        tbQuestion.setTenantId(getTenantId());
        List<TbQuestion> list = tbQuestionService.selectTbQuestionList(tbQuestion);
        return AjaxResult.success(list);
    }

    /**
     * 导出问题管理列表
     */
    @RequiresPermissions("business:Question:export")
    @Log(title = "问题管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbQuestion tbQuestion)
    {
        List<TbQuestion> list = tbQuestionService.selectTbQuestionList(tbQuestion);
        ExcelUtil<TbQuestion> util = new ExcelUtil<TbQuestion>(TbQuestion.class);
        return util.exportExcel(list, "问题管理数据");
    }

    /**
     * 新增问题管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    @GetMapping("/addContact")
    public String addContact()
    {
        return prefix + "/addContact";
    }

    /**
     * 新增保存问题管理
     */
    @RequiresPermissions("business:Question:add")
    @Log(title = "问题管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbQuestion tbQuestion)
    {
        return toAjax(tbQuestionService.insertTbQuestion(tbQuestion));
    }

    /**
     * 修改问题管理
     */
    @RequiresPermissions("business:Question:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbQuestion tbQuestion = tbQuestionService.selectTbQuestionById(id);
        mmap.put("tbQuestion", tbQuestion);
        return prefix + "/edit";
    }
    @RequiresPermissions("business:Question:edit")
    @GetMapping("/editContact/{id}")
    public String editContact(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbQuestion tbQuestion = tbQuestionService.selectTbQuestionById(id);
        mmap.put("tbQuestion", tbQuestion);
        return prefix + "/editContact";
    }
    /**
     * 详情问题管理
     */
    @RequiresPermissions("business:Question:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbQuestion tbQuestion = tbQuestionService.selectTbQuestionById(id);
        return success(tbQuestion);
    }

    /**
     * 修改保存问题管理
     */
    @RequiresPermissions("business:Question:edit")
    @Log(title = "问题管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbQuestion tbQuestion)
    {
        return toAjax(tbQuestionService.updateTbQuestion(tbQuestion));
    }

    /**
     * 删除问题管理
     */
    @RequiresPermissions("business:Question:remove")
    @Log(title = "问题管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbQuestionService.deleteTbQuestionByIds(ids));
    }
}
