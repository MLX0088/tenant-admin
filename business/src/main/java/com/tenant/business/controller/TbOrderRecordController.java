package com.tenant.business.controller;

import java.math.BigDecimal;
import java.util.*;

import com.tenant.business.domain.TbChatMessage;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.domain.TbVipPay;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.business.service.ITbVipPayService;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbOrderRecord;
import com.tenant.business.service.ITbOrderRecordService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 订单记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/OrderRecord")
public class TbOrderRecordController extends BaseController
{
    private String prefix = "business/OrderRecord";

    @Autowired
    private ITbOrderRecordService tbOrderRecordService;
    @Autowired
    private ITbVipPayService tbVipPayService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("business:OrderRecord:view")
    @GetMapping()
    public String OrderRecord()
    {
        return prefix + "/OrderRecord";
    }

    /**
     * 查询订单记录列表
     */
    @RequiresPermissions("business:OrderRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbOrderRecord tbOrderRecord)
    {
        startPage();
        List<TbOrderRecord> list = tbOrderRecordService.selectTbOrderRecordList(tbOrderRecord);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public TableDataInfo listForApp(TbOrderRecord tbOrderRecord)
    {
        startPage();
        tbOrderRecord.setTenantId(getTenantId());
        tbOrderRecord.setUserId(ShiroUtils.getUserId());
        List<TbOrderRecord> list = tbOrderRecordService.selectTbOrderRecordListForApp(tbOrderRecord);
        return getDataTable(list);
    }

    @GetMapping("/v1/availableCount")
    @ResponseBody
    public AjaxResult availableCount()
    {
        TbOrderRecord tbOrderRecord = new TbOrderRecord();
        tbOrderRecord.setUserId(ShiroUtils.getUserId());
        int count = tbOrderRecordService.availableCount(tbOrderRecord);

        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());

        return success(config.getMaxMinusCount()-count>=0?config.getMaxMinusCount()-count:0);
    }

    /**
     * 导出订单记录列表
     */
    @RequiresPermissions("business:OrderRecord:export")
    @Log(title = "订单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbOrderRecord tbOrderRecord)
    {
        List<TbOrderRecord> list = tbOrderRecordService.selectTbOrderRecordList(tbOrderRecord);
        ExcelUtil<TbOrderRecord> util = new ExcelUtil<TbOrderRecord>(TbOrderRecord.class);
        return util.exportExcel(list, "订单记录数据");
    }

    /**
     * 新增订单记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单记录
     */
    @RequiresPermissions("business:OrderRecord:add")
    @Log(title = "订单记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbOrderRecord tbOrderRecord)
    {
        SysUser user = sysUserService.selectUserById(tbOrderRecord.getUserId());
        if(user == null || user.getDeptId()==null || user.getDeptId().longValue()!=getTenantId().longValue()){
            return AjaxResult.error("此用户不存在");
        }

        if(tbOrderRecord.getStatus()==1){
            if(user.getScore()==null){
                user.setScore(new BigDecimal(0));
            }
            if(tbOrderRecord.getType() == 1 || tbOrderRecord.getType() == 3){
                if(user.getTotalAmount()==null){
                    user.setTotalAmount(0l);
                }
                user.setTotalAmount(user.getTotalAmount()+tbOrderRecord.getScore().longValue());
            }
            if(tbOrderRecord.getType() == 2 || tbOrderRecord.getType() == 4){
                user.setScore(user.getScore().subtract(tbOrderRecord.getScore()));
            }else{
                user.setScore(user.getScore().add(tbOrderRecord.getScore()));
            }

            sysUserService.updateUser(user);
            tbOrderRecord.setLeftScore(user.getScore());
            tbOrderRecord.setUserName(user.getUserName());
        }
        return toAjax(tbOrderRecordService.insertTbOrderRecord(tbOrderRecord));
    }

    @Log(title = "订单记录", businessType = BusinessType.INSERT)
    @PostMapping("/v1/add")
    @ResponseBody
    public AjaxResult addSaveForApp(@RequestBody TbOrderRecord tbOrderRecord)
    {
        SysUser user = sysUserService.selectUserById(ShiroUtils.getUserId());
        if(tbOrderRecord.getType()==2){
            tbOrderRecord.setUserId(ShiroUtils.getUserId());
            int count = tbOrderRecordService.availableCount(tbOrderRecord);

            TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());

            if(config.getMaxMinusCount()-count <= 0){
                return AjaxResult.error("剩余下分次数不足，请明天再试");
            }
            if(user.getScore().compareTo(tbOrderRecord.getScore()) < 0 ){
                return AjaxResult.error("余分不足"+tbOrderRecord.getScore().doubleValue());
            }
        }
        tbOrderRecord.setUserName(user.getUserName());

        TbVipPay pay = tbVipPayService.selectTbVipPayById(tbOrderRecord.getVipAccountId());
        tbOrderRecord.setTenantId(getTenantId());
        tbOrderRecord.setAccount(pay.getAccount());
        tbOrderRecord.setBankName(pay.getBankName());
        tbOrderRecord.setImageId(pay.getImageId());
        tbOrderRecord.setName(pay.getName());
        tbOrderRecord.setPayType(pay.getPayType());
        tbOrderRecord.setStatus(0);
        tbOrderRecord.setUserId(user.getUserId());

        int result = tbOrderRecordService.insertTbOrderRecord(tbOrderRecord);
        if(result>0 && tbOrderRecord.getType()==2){
            user = sysUserService.selectUserById(user.getUserId());
            SysUser temp = new SysUser();
            temp.setUserId(user.getUserId());
            temp.setScore(user.getScore().subtract(tbOrderRecord.getScore()));
            sysUserService.updateUser(temp);
        }
        return toAjax(result);
    }

    /**
     * 修改订单记录
     */
    @RequiresPermissions("business:OrderRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbOrderRecord tbOrderRecord = tbOrderRecordService.selectTbOrderRecordById(id);
        mmap.put("tbOrderRecord", tbOrderRecord);
        return prefix + "/edit";
    }
    @RequiresPermissions("business:OrderRecord:edit")
    @GetMapping("/audit/{id}")
    public String audit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbOrderRecord tbOrderRecord = tbOrderRecordService.selectTbOrderRecordById(id);
        mmap.put("tbOrderRecord", tbOrderRecord);
        return prefix + "/audit";
    }
    /**
     * 详情订单记录
     */
    @RequiresPermissions("business:OrderRecord:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbOrderRecord tbOrderRecord = tbOrderRecordService.selectTbOrderRecordById(id);
        return success(tbOrderRecord);
    }

    /**
     * 修改保存订单记录
     */
    @RequiresPermissions("business:OrderRecord:edit")
    @Log(title = "订单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbOrderRecord tbOrderRecord)
    {
        return toAjax(tbOrderRecordService.updateTbOrderRecord(tbOrderRecord));
    }

    /**
     * 修改保存订单记录
     */
    @RequiresPermissions("business:OrderRecord:edit")
    @Log(title = "订单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult auditSave(TbOrderRecord tbOrderRecord)
    {
        TbOrderRecord temp = tbOrderRecordService.selectTbOrderRecordById(tbOrderRecord.getId());
        temp.setStatus(tbOrderRecord.getStatus());
        temp.setRemark(tbOrderRecord.getRemark());
        temp.setAuditUserId(ShiroUtils.getUserId());
        temp.setAuditUserName(ShiroUtils.getSysUser().getUserName());
        temp.setAuditTime(new Date());
        if(tbOrderRecord.getStatus()==1){

            SysUser user = sysUserService.selectUserById(temp.getUserId());
            if(user.getScore()==null){
                user.setScore(new BigDecimal(0));
            }
            if(temp.getType() == 1 || temp.getType() == 3){
                if(user.getTotalAmount()==null){
                    user.setTotalAmount(0l);
                }
                user.setTotalAmount(user.getTotalAmount()+temp.getScore().longValue());
                user.setScore(user.getScore().add(temp.getScore()));
            }

            sysUserService.updateUser(user);
            temp.setLeftScore(user.getScore());
        }else{
            if(temp.getType()==2){
                SysUser user = sysUserService.selectUserById(temp.getUserId());
                user.setScore(user.getScore().add(temp.getScore()));
                sysUserService.updateUser(user);
                temp.setLeftScore(user.getScore());
            }
        }

        return toAjax(tbOrderRecordService.updateTbOrderRecord(temp));
    }

    /**
     * 删除订单记录
     */
    @RequiresPermissions("business:OrderRecord:remove")
    @Log(title = "订单记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbOrderRecordService.deleteTbOrderRecordByIds(ids));
    }

    @PostMapping("/auditScore")
    @ResponseBody
    public AjaxResult auditScore(TbOrderRecord tbOrderRecord){
        double auditScore = tbOrderRecordService.auditScore(tbOrderRecord);
        Map<String,Object> map = new HashMap();
        map.put("auditScore",auditScore);
        return AjaxResult.success(map);
    }
}
