package com.tenant.business.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbImages;
import com.tenant.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbVipPay;
import com.tenant.business.service.ITbVipPayService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 会员支付信息Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/VipPay")
public class TbVipPayController extends BaseController
{
    private String prefix = "business/VipPay";

    @Autowired
    private ITbVipPayService tbVipPayService;
    @Autowired
    private TbImagesController imagesController;

    @RequiresPermissions("business:VipPay:view")
    @GetMapping()
    public String VipPay()
    {
        return prefix + "/VipPay";
    }

    /**
     * 查询会员支付信息列表
     */
    @RequiresPermissions("business:VipPay:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbVipPay tbVipPay)
    {
        startPage();
        List<TbVipPay> list = tbVipPayService.selectTbVipPayList(tbVipPay);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        TbVipPay tbVipPay = new TbVipPay();
        tbVipPay.setTenantId(getTenantId());
        List<TbVipPay> list = tbVipPayService.selectTbVipPayList(tbVipPay);
        return success(list);
    }

    /**
     * 导出会员支付信息列表
     */
    @RequiresPermissions("business:VipPay:export")
    @Log(title = "会员支付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbVipPay tbVipPay)
    {
        List<TbVipPay> list = tbVipPayService.selectTbVipPayList(tbVipPay);
        ExcelUtil<TbVipPay> util = new ExcelUtil<TbVipPay>(TbVipPay.class);
        return util.exportExcel(list, "会员支付信息数据");
    }

    /**
     * 新增会员支付信息
     */
    @GetMapping("/add")
    public String add(TbVipPay tbVipPay, Model model)
    {
        if(tbVipPay != null){
            model.addAttribute("userId",tbVipPay.getUserId());
            model.addAttribute("name",tbVipPay.getName());
            model.addAttribute("tenantId",tbVipPay.getTenantId());
        }
        return prefix + "/add";
    }

    /**
     * 新增保存会员支付信息
     */
    @RequiresPermissions("business:VipPay:add")
    @Log(title = "会员支付信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbVipPay tbVipPay)
    {
        return toAjax(tbVipPayService.insertTbVipPay(tbVipPay));
    }

    @Log(title = "会员支付信息", businessType = BusinessType.INSERT)
    @PostMapping("/v1/add")
    @ResponseBody
    public AjaxResult addSaveForApp(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam Map<String, String> map) throws IOException {
        TbVipPay tbVipPay = new TbVipPay();
        tbVipPay.setName(map.get("name"));
        tbVipPay.setAccount(map.get("account"));
        tbVipPay.setPayType(map.get("payType"));
        tbVipPay.setBankName(map.get("bankName"));
        tbVipPay.setOpenBankName(map.get("openBankName"));
        tbVipPay.setTenantId(getTenantId());
        tbVipPay.setUserId(ShiroUtils.getUserId());

        if(file != null && !file.isEmpty()){
            TbImages tbImages = imagesController.upload_image_inner(file);
            if(tbImages!=null && tbImages.getId()!=null && tbImages.getId()>0){
                tbVipPay.setImageId(tbImages.getId());
            }
        }

        tbVipPayService.insertTbVipPay(tbVipPay);
        if(tbVipPay.getId()<=1){
            return AjaxResult.error("新增失败");
        }
        tbVipPay = tbVipPayService.selectTbVipPayById(tbVipPay.getId());
        return success(tbVipPay);
    }

    /**
     * 修改会员支付信息
     */
    @RequiresPermissions("business:VipPay:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbVipPay tbVipPay = tbVipPayService.selectTbVipPayById(id);
        mmap.put("tbVipPay", tbVipPay);
        return prefix + "/edit";
    }
    /**
     * 详情会员支付信息
     */
    @RequiresPermissions("business:VipPay:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbVipPay tbVipPay = tbVipPayService.selectTbVipPayById(id);
        return success(tbVipPay);
    }

    /**
     * 修改保存会员支付信息
     */
    @RequiresPermissions("business:VipPay:edit")
    @Log(title = "会员支付信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbVipPay tbVipPay)
    {
        return toAjax(tbVipPayService.updateTbVipPay(tbVipPay));
    }

    /**
     * 删除会员支付信息
     */
    @RequiresPermissions("business:VipPay:remove")
    @Log(title = "会员支付信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbVipPayService.deleteTbVipPayByIds(ids));
    }
}
