package com.tenant.web.controller.system;

import java.util.List;

import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.business.service.ITbRobotService;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.common.core.domain.entity.SysRole;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.DateUtils;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.framework.shiro.service.SysPasswordService;
import com.tenant.system.service.ISysRoleService;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.annotation.Log;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.Ztree;
import com.tenant.common.core.domain.entity.SysDept;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.service.ISysDeptService;

/**
 * 部门信息
 * 
 * @author tenant
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbAvatarService tbAvatarService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ITbRobotService tbRobotService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept)
    {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        if (!getSysUser().isAdmin())
        {
            parentId = getSysUser().getDeptId();
        }
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(@Validated SysDept dept)
    {
        if (!deptService.checkDeptNameUnique(dept))
        {
            return error("新增租客'" + dept.getDeptName() + "'失败，租客名称已存在");
        }
        if (tbTenantConfigService.checkInviteCode(dept.getTenantConfig().getInviteCode(),-1) > 0)
        {
            return error("新增租客'" + dept.getDeptName() + "'失败，邀请码重复");
        }
        SysUser userTemp = sysUserService.selectUserByLoginName(dept.getPhone());
        if(userTemp != null){
            return error("新增租客'" + dept.getDeptName() + "'失败，手机号在系统中已经注册");
        }
        dept.setCreateBy(getLoginName());
        int count = deptService.insertDept(dept);

        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(0l);
        config.setId(null);
        config.setTenantId(dept.getDeptId());
        config.setInviteCode(dept.getTenantConfig().getInviteCode());
        config.setNavUrl(dept.getTenantConfig().getNavUrl());
        config.setClientUrl(dept.getTenantConfig().getClientUrl());
        config.setAndroidUrl(dept.getTenantConfig().getAndroidUrl());
        config.setIosUrl(dept.getTenantConfig().getIosUrl());
        tbTenantConfigService.insertTbTenantConfig(config);

        tbTenantConfigService.generateTenantConfigs(dept.getDeptId());
        int randomNum = (int) (Math.random() * (100 - 80 + 1)) + 80;
        tbRobotService.createRebotForTenantId(dept.getDeptId(),randomNum,null);

        SysUser user = new SysUser();
        user.setDeptId(dept.getDeptId());
        user.setUserName(dept.getLeader());
        user.setLoginName(dept.getPhone());
        user.setUserType("00");
        user.setEmail(dept.getEmail());
        user.setPermission("管理员");
        user.setHeadImageId(tbAvatarService.getRandomIdForTenant(dept.getDeptId()));
        user.setPhonenumber(dept.getPhone());
        user.setPassword("defaultAdmin123");
        user.setPwdUpdateDate(DateUtils.getNowDate());
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));

        SysRole roleParam = new SysRole();
        roleParam.setRoleKey("master");
        List<SysRole> roleList = sysRoleService.selectRoleList(roleParam);
        Long[] roleIds = new Long[roleList.size()];
        for (int i = 0;i<roleList.size();i++) {
            roleIds[i] = roleList.get(i).getRoleId();
        }
        user.setRoleIds(roleIds);

        sysUserService.insertUser(user);
        return toAjax(count);
    }

    /**
     * 修改部门
     */
    @RequiresPermissions("system:dept:edit")
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        deptService.checkDeptDataScope(deptId);
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 修改保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept)
    {
        Long deptId = dept.getDeptId();
        deptService.checkDeptDataScope(deptId);
        if (!deptService.checkDeptNameUnique(dept))
        {
            return error("修改租客'" + dept.getDeptName() + "'失败，租客名称已存在");
        }
        if (tbTenantConfigService.checkInviteCode(dept.getTenantConfig().getInviteCode(),deptId.longValue()) > 0)
        {
            return error("修改租客'" + dept.getDeptName() + "'失败，邀请码重复");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && deptService.selectNormalChildrenDeptById(deptId) > 0)
        {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }

        dept.setUpdateBy(getLoginName());
        int count = deptService.updateDept(dept);

        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(deptId);
        config.setInviteCode(dept.getTenantConfig().getInviteCode());
        config.setNavUrl(dept.getTenantConfig().getNavUrl());
        config.setClientUrl(dept.getTenantConfig().getClientUrl());
        config.setAndroidUrl(dept.getTenantConfig().getAndroidUrl());
        config.setIosUrl(dept.getTenantConfig().getIosUrl());
        tbTenantConfigService.updateTbTenantConfig(config);
        return toAjax(count);
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectDeptCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("部门存在用户,不允许删除");
        }
        deptService.checkDeptDataScope(deptId);
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public boolean checkDeptNameUnique(SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 选择部门树
     * 
     * @param deptId 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectDeptTree/{deptId}", "/selectDeptTree/{deptId}/{excludeId}" })
    public String selectDeptTree(@PathVariable("deptId") Long deptId,
            @PathVariable(value = "excludeId", required = false) Long excludeId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId)
    {
        SysDept dept = new SysDept();
        dept.setExcludeId(excludeId);
        List<Ztree> ztrees = deptService.selectDeptTreeExcludeChild(dept);
        return ztrees;
    }
}
