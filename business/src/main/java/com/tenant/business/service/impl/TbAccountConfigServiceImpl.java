package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbAccountConfigMapper;
import com.tenant.business.domain.TbAccountConfig;
import com.tenant.business.service.ITbAccountConfigService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 上/下分账号设置Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbAccountConfigServiceImpl implements ITbAccountConfigService 
{
    @Autowired
    private TbAccountConfigMapper tbAccountConfigMapper;

    /**
     * 查询上/下分账号设置
     * 
     * @param id 上/下分账号设置主键
     * @return 上/下分账号设置
     */
    @Override
    public TbAccountConfig selectTbAccountConfigById(Long id)
    {
        return tbAccountConfigMapper.selectTbAccountConfigById(id);
    }

    /**
     * 查询上/下分账号设置列表
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 上/下分账号设置
     */
    @Override
    public List<TbAccountConfig> selectTbAccountConfigList(TbAccountConfig tbAccountConfig)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbAccountConfig.setCreateBy(sysUser.getLoginName());
        }
        return tbAccountConfigMapper.selectTbAccountConfigList(tbAccountConfig);
    }

    /**
     * 新增上/下分账号设置
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 结果
     */
    @Override
    public int insertTbAccountConfig(TbAccountConfig tbAccountConfig)
    {
        tbAccountConfig.setCreateTime(DateUtils.getNowDate());
        tbAccountConfig.setCreateBy(ShiroUtils.getLoginName());
        return tbAccountConfigMapper.insertTbAccountConfig(tbAccountConfig);
    }

    /**
     * 修改上/下分账号设置
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 结果
     */
    @Override
    public int updateTbAccountConfig(TbAccountConfig tbAccountConfig)
    {
        tbAccountConfig.setUpdateTime(DateUtils.getNowDate());
         tbAccountConfig.setUpdateBy(ShiroUtils.getLoginName());
        return tbAccountConfigMapper.updateTbAccountConfig(tbAccountConfig);
    }

    /**
     * 批量删除上/下分账号设置
     * 
     * @param ids 需要删除的上/下分账号设置主键
     * @return 结果
     */
    @Override
    public int deleteTbAccountConfigByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbAccountConfigMapper.deleteTbAccountConfigByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除上/下分账号设置信息
     * 
     * @param id 上/下分账号设置主键
     * @return 结果
     */
    @Override
    public int deleteTbAccountConfigById(Long id)
    {
        return tbAccountConfigMapper.deleteTbAccountConfigById(id);
    }
}
