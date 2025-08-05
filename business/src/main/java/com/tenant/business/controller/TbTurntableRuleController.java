package com.tenant.business.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tenant.business.domain.TbOrderRecord;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbOrderRecordService;
import com.tenant.business.service.ITbTenantConfigService;
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
import com.tenant.business.domain.TbTurntableRule;
import com.tenant.business.service.ITbTurntableRuleService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 转盘管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/TurntableRule")
public class TbTurntableRuleController extends BaseController
{
    private String prefix = "business/TurntableRule";

    @Autowired
    private ITbTurntableRuleService tbTurntableRuleService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbOrderRecordService tbOrderRecordService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("business:TurntableRule:view")
    @GetMapping()
    public String TurntableRule()
    {
        return prefix + "/TurntableRule";
    }

    /**
     * 查询转盘管理列表
     */
    @RequiresPermissions("business:TurntableRule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbTurntableRule tbTurntableRule)
    {
        startPage();
        List<TbTurntableRule> list = tbTurntableRuleService.selectTbTurntableRuleList(tbTurntableRule);
        return getDataTable(list);
    }

    @GetMapping("/v1/list")
    @ResponseBody
    public AjaxResult listForApp()
    {
        TbTurntableRule tbTurntableRule = new TbTurntableRule();
        tbTurntableRule.setTenantId(getTenantId());
        List<TbTurntableRule> list = tbTurntableRuleService.selectTbTurntableRuleList(tbTurntableRule);
        for (TbTurntableRule temp : list ) {
            temp.setRate(null);
            temp.setTenantId(null);
        }
        return success(list);
    }

    @Log(title = "超级大转盘", businessType = BusinessType.INSERT)
    @PostMapping("/v1/luckyDraw")
    @ResponseBody
    public AjaxResult luckyDraw()
    {
        TbOrderRecord order = new TbOrderRecord();
        order.setUserId(ShiroUtils.getUserId());
        double liushui = tbOrderRecordService.todayLiushui(order);

        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
        if(config.getTurntableScore()>liushui){
            return error("未满足抽奖条件，还差："+(config.getTurntableScore()-liushui));
        }

        int count = tbOrderRecordService.todayLuckyDrawCount(order);
        if(count > 0){
            return error("每天只能参与一次幸运大转盘！");
        }

        TbTurntableRule tbTurntableRule = new TbTurntableRule();
        tbTurntableRule.setTenantId(getTenantId());
        List<TbTurntableRule> list = tbTurntableRuleService.selectTbTurntableRuleList(tbTurntableRule);

        int randomValue = new Random().nextInt(100); // [0,99]
        TbTurntableRule result = null;
        int sum = 0;
        int i = 0;
        for (TbTurntableRule obj : list) {
            sum += obj.getRate();
            if (sum > randomValue) {
                result = obj;
                break;
            }
            i++;
        }
        // 兜底逻辑（理论上不会执行）
        if(result == null){
            result = list.get(0);
        }

        SysUser user = sysUserService.selectUserById(ShiroUtils.getUserId());
        SysUser temp = new SysUser();
        temp.setUserId(user.getUserId());
        temp.setScore(user.getScore().add(new BigDecimal(result.getAmount())));
        sysUserService.updateUserInfo(temp);

        order.setScore(new BigDecimal(result.getAmount()) );
        order.setTenantId(getTenantId());
        order.setStatus(1);
        order.setType(8l);
        order.setUserName(user.getUserName());
        order.setLeftScore(temp.getScore());
        tbOrderRecordService.insertTbOrderRecord(order);

        return success(i);
    }

    /**
     * 导出转盘管理列表
     */
    @RequiresPermissions("business:TurntableRule:export")
    @Log(title = "转盘管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbTurntableRule tbTurntableRule)
    {
        List<TbTurntableRule> list = tbTurntableRuleService.selectTbTurntableRuleList(tbTurntableRule);
        ExcelUtil<TbTurntableRule> util = new ExcelUtil<TbTurntableRule>(TbTurntableRule.class);
        return util.exportExcel(list, "转盘管理数据");
    }

    /**
     * 新增转盘管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存转盘管理
     */
    @RequiresPermissions("business:TurntableRule:add")
    @Log(title = "转盘管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbTurntableRule tbTurntableRule)
    {
        return toAjax(tbTurntableRuleService.insertTbTurntableRule(tbTurntableRule));
    }

    /**
     * 修改转盘管理
     */
    @RequiresPermissions("business:TurntableRule:edit")
    @GetMapping("/edit")
    public String edit( ModelMap mmap)
    {
        TbTurntableRule tbTurntableRule = new TbTurntableRule();
        tbTurntableRule.setTenantId(getSelectDeptId());
        List<TbTurntableRule> list = tbTurntableRuleService.selectTbTurntableRuleList(tbTurntableRule);
        TbTenantConfig config =  tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
        mmap.put("list", list);
        mmap.put("turntableScore", config.getTurntableScore());
        mmap.put("turntableRule", config.getTurntableRule());
        return prefix + "/edit";
    }
    /**
     * 详情转盘管理
     */
    @RequiresPermissions("business:TurntableRule:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbTurntableRule tbTurntableRule = tbTurntableRuleService.selectTbTurntableRuleById(id);
        return success(tbTurntableRule);
    }

    public class DataModel{
        ArrayList<TbTurntableRule> list = new ArrayList<>();
        Long turntableScore;
        String turntableRule;

        public ArrayList<TbTurntableRule> getList() {
            return list;
        }

        public void setList(ArrayList<TbTurntableRule> list) {
            this.list = list;
        }

        public Long getTurntableScore() {
            return turntableScore;
        }

        public void setTurntableScore(Long turntableScore) {
            this.turntableScore = turntableScore;
        }

        public String getTurntableRule() {
            return turntableRule;
        }

        public void setTurntableRule(String turntableRule) {
            this.turntableRule = turntableRule;
        }
    }

    /**
     * 修改保存转盘管理
     */
    @RequiresPermissions("business:TurntableRule:edit")
    @Log(title = "转盘管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DataModel dataModal)
    {
        int count = 0;
        for (TbTurntableRule obj :dataModal.list) {
            count += tbTurntableRuleService.updateTbTurntableRule(obj);
        }
        if(count>0){
            TbTenantConfig config =  tbTenantConfigService.selectTbTenantConfigByTenantId(getTenantId());
            config.setTurntableScore(dataModal.turntableScore);
            config.setTurntableRule(dataModal.turntableRule);
            tbTenantConfigService.updateTbTenantConfig(config);
        }
        return toAjax(count);
    }

    /**
     * 删除转盘管理
     */
    @RequiresPermissions("business:TurntableRule:remove")
    @Log(title = "转盘管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbTurntableRuleService.deleteTbTurntableRuleByIds(ids));
    }
}
