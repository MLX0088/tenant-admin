package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbTurntableRuleMapper;
import com.tenant.business.domain.TbTurntableRule;
import com.tenant.business.service.ITbTurntableRuleService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 转盘管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbTurntableRuleServiceImpl implements ITbTurntableRuleService 
{
    @Autowired
    private TbTurntableRuleMapper tbTurntableRuleMapper;

    /**
     * 查询转盘管理
     * 
     * @param id 转盘管理主键
     * @return 转盘管理
     */
    @Override
    public TbTurntableRule selectTbTurntableRuleById(Long id)
    {
        return tbTurntableRuleMapper.selectTbTurntableRuleById(id);
    }

    /**
     * 查询转盘管理列表
     * 
     * @param tbTurntableRule 转盘管理
     * @return 转盘管理
     */
    @Override
    public List<TbTurntableRule> selectTbTurntableRuleList(TbTurntableRule tbTurntableRule)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbTurntableRule.setCreateBy(sysUser.getLoginName());
        }
        return tbTurntableRuleMapper.selectTbTurntableRuleList(tbTurntableRule);
    }

    /**
     * 新增转盘管理
     * 
     * @param tbTurntableRule 转盘管理
     * @return 结果
     */
    @Override
    public int insertTbTurntableRule(TbTurntableRule tbTurntableRule)
    {
        tbTurntableRule.setCreateTime(DateUtils.getNowDate());
        tbTurntableRule.setCreateBy(ShiroUtils.getLoginName());
        return tbTurntableRuleMapper.insertTbTurntableRule(tbTurntableRule);
    }

    /**
     * 修改转盘管理
     * 
     * @param tbTurntableRule 转盘管理
     * @return 结果
     */
    @Override
    public int updateTbTurntableRule(TbTurntableRule tbTurntableRule)
    {
        tbTurntableRule.setUpdateTime(DateUtils.getNowDate());
         tbTurntableRule.setUpdateBy(ShiroUtils.getLoginName());
        return tbTurntableRuleMapper.updateTbTurntableRule(tbTurntableRule);
    }

    /**
     * 批量删除转盘管理
     * 
     * @param ids 需要删除的转盘管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTurntableRuleByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbTurntableRuleMapper.deleteTbTurntableRuleByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除转盘管理信息
     * 
     * @param id 转盘管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTurntableRuleById(Long id)
    {
        return tbTurntableRuleMapper.deleteTbTurntableRuleById(id);
    }
}
