package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbIpBlackMapper;
import com.tenant.business.domain.TbIpBlack;
import com.tenant.business.service.ITbIpBlackService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * IP黑名单Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbIpBlackServiceImpl implements ITbIpBlackService 
{
    @Autowired
    private TbIpBlackMapper tbIpBlackMapper;

    /**
     * 查询IP黑名单
     * 
     * @param id IP黑名单主键
     * @return IP黑名单
     */
    @Override
    public TbIpBlack selectTbIpBlackById(Long id)
    {
        return tbIpBlackMapper.selectTbIpBlackById(id);
    }

    /**
     * 查询IP黑名单列表
     * 
     * @param tbIpBlack IP黑名单
     * @return IP黑名单
     */
    @Override
    public List<TbIpBlack> selectTbIpBlackList(TbIpBlack tbIpBlack)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbIpBlack.setCreateBy(sysUser.getLoginName());
        }
        return tbIpBlackMapper.selectTbIpBlackList(tbIpBlack);
    }

    /**
     * 新增IP黑名单
     * 
     * @param tbIpBlack IP黑名单
     * @return 结果
     */
    @Override
    public int insertTbIpBlack(TbIpBlack tbIpBlack)
    {
        tbIpBlack.setCreateTime(DateUtils.getNowDate());
        tbIpBlack.setCreateBy(ShiroUtils.getLoginName());
        return tbIpBlackMapper.insertTbIpBlack(tbIpBlack);
    }

    /**
     * 修改IP黑名单
     * 
     * @param tbIpBlack IP黑名单
     * @return 结果
     */
    @Override
    public int updateTbIpBlack(TbIpBlack tbIpBlack)
    {
        tbIpBlack.setUpdateTime(DateUtils.getNowDate());
         tbIpBlack.setUpdateBy(ShiroUtils.getLoginName());
        return tbIpBlackMapper.updateTbIpBlack(tbIpBlack);
    }

    /**
     * 批量删除IP黑名单
     * 
     * @param ids 需要删除的IP黑名单主键
     * @return 结果
     */
    @Override
    public int deleteTbIpBlackByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbIpBlackMapper.deleteTbIpBlackByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除IP黑名单信息
     * 
     * @param id IP黑名单主键
     * @return 结果
     */
    @Override
    public int deleteTbIpBlackById(Long id)
    {
        return tbIpBlackMapper.deleteTbIpBlackById(id);
    }
}
