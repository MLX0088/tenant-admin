package com.tenant.system.service.impl;

import java.util.List;

import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.DateUtils;
import com.tenant.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.system.mapper.SysAdMapper;
import com.tenant.system.domain.SysAd;
import com.tenant.system.service.ISysAdService;
import com.tenant.common.core.text.Convert;

/**
 * 广告管理Service业务层处理
 * 
 * @author luanyu
 * @date 2023-03-14
 */
@Service
public class SysAdServiceImpl implements ISysAdService 
{
    @Autowired
    private SysAdMapper sysAdMapper;

    /**
     * 查询广告管理
     * 
     * @param id 广告管理主键
     * @return 广告管理
     */
    @Override
    public SysAd selectSysAdById(Long id)
    {
        return sysAdMapper.selectSysAdById(id);
    }

    /**
     * 查询广告管理列表
     * 
     * @param sysAd 广告管理
     * @return 广告管理
     */
    @Override
    public List<SysAd> selectSysAdList(SysAd sysAd)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.SYSTEM_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
           sysAd.setCreateBy(null);
        }else {
            sysAd.setCreateBy("user");      //前端查询去掉到期的
        }
        return sysAdMapper.selectSysAdList(sysAd);
    }

    /**
     * 新增广告管理
     * 
     * @param sysAd 广告管理
     * @return 结果
     */
    @Override
    public int insertSysAd(SysAd sysAd)
    {
        sysAd.setCreateTime(DateUtils.getNowDate());
        sysAd.setCreateBy(ShiroUtils.getLoginName());
        return sysAdMapper.insertSysAd(sysAd);
    }

    /**
     * 修改广告管理
     * 
     * @param sysAd 广告管理
     * @return 结果
     */
    @Override
    public int updateSysAd(SysAd sysAd)
    {
        sysAd.setUpdateTime(DateUtils.getNowDate());
         sysAd.setUpdateBy(ShiroUtils.getLoginName());
        return sysAdMapper.updateSysAd(sysAd);
    }

    /**
     * 批量删除广告管理
     * 
     * @param ids 需要删除的广告管理主键
     * @return 结果
     */
    @Override
    public int deleteSysAdByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return sysAdMapper.deleteSysAdByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除广告管理信息
     * 
     * @param id 广告管理主键
     * @return 结果
     */
    @Override
    public int deleteSysAdById(Long id)
    {
        return sysAdMapper.deleteSysAdById(id);
    }
}
