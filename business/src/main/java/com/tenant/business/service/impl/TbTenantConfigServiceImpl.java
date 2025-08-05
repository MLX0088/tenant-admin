package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbTenantConfigMapper;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 租客基础设置Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbTenantConfigServiceImpl implements ITbTenantConfigService 
{
    @Autowired
    private TbTenantConfigMapper tbTenantConfigMapper;

    /**
     * 查询租客基础设置
     * 
     * @param id 租客基础设置主键
     * @return 租客基础设置
     */
    @Override
    public TbTenantConfig selectTbTenantConfigById(Long id)
    {
        return tbTenantConfigMapper.selectTbTenantConfigById(id);
    }
    @Override
    public TbTenantConfig selectTbTenantConfigByTenantId(Long id)
    {
        return tbTenantConfigMapper.selectTbTenantConfigByTenantId(id);
    }

    /**
     * 查询租客基础设置列表
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 租客基础设置
     */
    @Override
    public List<TbTenantConfig> selectTbTenantConfigList(TbTenantConfig tbTenantConfig)
    {

        return tbTenantConfigMapper.selectTbTenantConfigList(tbTenantConfig);
    }

    /**
     * 新增租客基础设置
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 结果
     */
    @Override
    public int insertTbTenantConfig(TbTenantConfig tbTenantConfig)
    {
        tbTenantConfig.setCreateTime(DateUtils.getNowDate());
        tbTenantConfig.setCreateBy(ShiroUtils.getLoginName());
        return tbTenantConfigMapper.insertTbTenantConfig(tbTenantConfig);
    }

    /**
     * 修改租客基础设置
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 结果
     */
    @Override
    public int updateTbTenantConfig(TbTenantConfig tbTenantConfig)
    {
        tbTenantConfig.setUpdateTime(DateUtils.getNowDate());
         tbTenantConfig.setUpdateBy(ShiroUtils.getLoginName());
        return tbTenantConfigMapper.updateTbTenantConfig(tbTenantConfig);
    }

    /**
     * 批量删除租客基础设置
     * 
     * @param ids 需要删除的租客基础设置主键
     * @return 结果
     */
    @Override
    public int deleteTbTenantConfigByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbTenantConfigMapper.deleteTbTenantConfigByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除租客基础设置信息
     * 
     * @param id 租客基础设置主键
     * @return 结果
     */
    @Override
    public int deleteTbTenantConfigById(Long id)
    {
        return tbTenantConfigMapper.deleteTbTenantConfigById(id);
    }

    @Override
    public int checkInviteCode(String inviteCode,long id){
        TbTenantConfig config = tbTenantConfigMapper.checkInviteCode(inviteCode);
        if(config == null || config.getTenantId().longValue()==id){
            return 0;
        }
        return 1;
    }

    @Override
    public TbTenantConfig getByInviteCode(String inviteCode){
        return tbTenantConfigMapper.checkInviteCode(inviteCode);
    }

    @Override
    public void generateTenantConfigs(Long id){
        tbTenantConfigMapper.generateTenantConfigs(id);
    }
}
