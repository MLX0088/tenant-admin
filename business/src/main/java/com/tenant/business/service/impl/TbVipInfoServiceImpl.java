package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbVipInfoMapper;
import com.tenant.business.domain.TbVipInfo;
import com.tenant.business.service.ITbVipInfoService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 会员信息Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbVipInfoServiceImpl implements ITbVipInfoService 
{
    @Autowired
    private TbVipInfoMapper tbVipInfoMapper;

    /**
     * 查询会员信息
     * 
     * @param id 会员信息主键
     * @return 会员信息
     */
    @Override
    public TbVipInfo selectTbVipInfoById(Long id)
    {
        return tbVipInfoMapper.selectTbVipInfoById(id);
    }

    /**
     * 查询会员信息列表
     * 
     * @param tbVipInfo 会员信息
     * @return 会员信息
     */
    @Override
    public List<TbVipInfo> selectTbVipInfoList(TbVipInfo tbVipInfo)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbVipInfo.setCreateBy(sysUser.getLoginName());
        }
        return tbVipInfoMapper.selectTbVipInfoList(tbVipInfo);
    }

    /**
     * 新增会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    @Override
    public int insertTbVipInfo(TbVipInfo tbVipInfo)
    {
        tbVipInfo.setCreateTime(DateUtils.getNowDate());
        tbVipInfo.setCreateBy(ShiroUtils.getLoginName());
        return tbVipInfoMapper.insertTbVipInfo(tbVipInfo);
    }

    /**
     * 修改会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    @Override
    public int updateTbVipInfo(TbVipInfo tbVipInfo)
    {
        tbVipInfo.setUpdateTime(DateUtils.getNowDate());
         tbVipInfo.setUpdateBy(ShiroUtils.getLoginName());
        return tbVipInfoMapper.updateTbVipInfo(tbVipInfo);
    }

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的会员信息主键
     * @return 结果
     */
    @Override
    public int deleteTbVipInfoByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbVipInfoMapper.deleteTbVipInfoByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除会员信息信息
     * 
     * @param id 会员信息主键
     * @return 结果
     */
    @Override
    public int deleteTbVipInfoById(Long id)
    {
        return tbVipInfoMapper.deleteTbVipInfoById(id);
    }
}
