package com.tenant.business.controller;

import com.tenant.business.domain.TbOrderRecord;
import com.tenant.business.domain.TbScoreRecord;
import com.tenant.business.service.ITbOrderRecordService;
import com.tenant.business.service.ITbScoreRecordService;
import com.tenant.common.annotation.Log;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.entity.SysDept;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.core.page.TableDataInfo;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.utils.StringUtils;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.system.service.ISysDeptService;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.system.domain.SysUserOnline;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单记录Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/dashboard")
public class DashboardController extends BaseController
{
    private String prefix = "business/Dashboard";

    @Autowired
    private ITbOrderRecordService tbOrderRecordService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @RequiresPermissions("business:OrderRecord:view")
    @GetMapping()
    public String dashboard()
    {
        return prefix + "/dashboard";
    }

    /**
     * 查询上分订单
     */
    @RequiresPermissions("business:OrderRecord:list")
    @PostMapping("/upList")
    @ResponseBody
    public TableDataInfo upList(TbOrderRecord tbOrderRecord)
    {
        startPage();
        tbOrderRecord.setTenantId(getTenantId());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        tbOrderRecord.setCreateTime(cal.getTime());
        List<TbOrderRecord> list = tbOrderRecordService.selectUpList(tbOrderRecord);
        return getDataTable(list);
    }

    /**
     * 查询下分订单
     */
    @RequiresPermissions("business:OrderRecord:list")
    @PostMapping("/downList")
    @ResponseBody
    public TableDataInfo downList(TbOrderRecord tbOrderRecord)
    {
        startPage();
        tbOrderRecord.setTenantId(getTenantId());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        tbOrderRecord.setCreateTime(cal.getTime());
        List<TbOrderRecord> list = tbOrderRecordService.selectDownList(tbOrderRecord);
        return getDataTable(list);
    }

    @GetMapping("/monitor")
    @ResponseBody
    public AjaxResult monitor()
    {
        Map<String,Object> map = new HashMap();
        long tenantId = getTenantId();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        Calendar calTmr = Calendar.getInstance();
        calTmr.set(Calendar.HOUR_OF_DAY,0);
        calTmr.set(Calendar.MINUTE,0);
        calTmr.set(Calendar.SECOND,0);
        calTmr.add(Calendar.DAY_OF_MONTH,1);

        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        int onlineNo = 0;
        Set<Long> userIdSet = new HashSet<>();
        while (it.hasNext())
        {
            SysUser user = getSession(it.next());
            if (StringUtils.isNotNull(user) && user.getDeptId()==tenantId)
            {
                if(userIdSet.contains(user.getUserId())){
                    continue;
                }
                userIdSet.add(user.getUserId());
                onlineNo++;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        TbOrderRecord tbOrderRecord = new TbOrderRecord();
        tbOrderRecord.setTenantId(tenantId);
        tbOrderRecord.setCreateTime(cal.getTime());
        Map<String,Object> mapOrder = tbOrderRecordService.statisticsDashboardRecord(tbOrderRecord);
        TbScoreRecord tbScoreRecord = new TbScoreRecord();
        tbScoreRecord.setTenantId(tenantId);
        tbScoreRecord.setBeginCreateTime(sdf.format(cal.getTime()));
        tbScoreRecord.setEndCreateTime(sdf.format(calTmr.getTime()));
        Map<String,Object> mapScore = tbScoreRecordService.statisticsDashboardRecord(tbScoreRecord);
        SysUser user = new SysUser();
        user.setDeptId(tenantId);
        Map<String,Object> mapUser = sysUserService.statisticsDashboardRecord(user);
        SysDept dept = sysDeptService.selectDeptById(tenantId);

        map.put("onlineNo",onlineNo);
        map.put("addPeople",mapUser.get("addPeople"));
        map.put("totalAddScore",mapOrder.get("addScore"));
        map.put("totalMinusScore",mapOrder.get("minusScore"));
        map.put("win",Double.parseDouble(mapOrder.get("addScore").toString()) - Double.parseDouble(mapOrder.get("minusScore").toString()));
        map.put("scorePerson",mapScore.get("scorePerson"));
        map.put("totalWin",mapScore.get("totalWin"));
        map.put("totalScore",mapUser.get("totalScore"));
        map.put("expireTime",sdf.format(dept.getExpireTime())+" - "+("0".equals(dept.getStatus())?"启用":"停用"));
        return AjaxResult.success(map);
    }

    /**
     * 新增保存订单记录
     */
    @RequiresPermissions("business:OrderRecord:add")
    @Log(title = "订单记录", businessType = BusinessType.INSERT)
    @PostMapping("/orderAdd")
    @ResponseBody
    public AjaxResult orderAdd(TbOrderRecord tbOrderRecord)
    {
        return toAjax(tbOrderRecordService.insertTbOrderRecord(tbOrderRecord));
    }


    private SysUser getSession(Session session)
    {
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj)
        {
            return null;
        }
        if (obj instanceof SimplePrincipalCollection)
        {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof SysUser)
            {
                SysUser sysUser = (SysUser) obj;
                return sysUser;
            }
        }
        return null;
    }
}
