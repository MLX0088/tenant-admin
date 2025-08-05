package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbVipGradeMapper;
import com.tenant.business.domain.TbVipGrade;
import com.tenant.business.service.ITbVipGradeService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 会员等级管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbVipGradeServiceImpl implements ITbVipGradeService 
{
    @Autowired
    private TbVipGradeMapper tbVipGradeMapper;

    /**
     * 查询会员等级管理
     * 
     * @param id 会员等级管理主键
     * @return 会员等级管理
     */
    @Override
    public TbVipGrade selectTbVipGradeById(Long id)
    {
        return tbVipGradeMapper.selectTbVipGradeById(id);
    }

    /**
     * 查询会员等级管理列表
     * 
     * @param tbVipGrade 会员等级管理
     * @return 会员等级管理
     */
    @Override
    public List<TbVipGrade> selectTbVipGradeList(TbVipGrade tbVipGrade)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbVipGrade.setCreateBy(sysUser.getLoginName());
        }
        return tbVipGradeMapper.selectTbVipGradeList(tbVipGrade);
    }

    /**
     * 新增会员等级管理
     * 
     * @param tbVipGrade 会员等级管理
     * @return 结果
     */
    @Override
    public int insertTbVipGrade(TbVipGrade tbVipGrade)
    {
        tbVipGrade.setCreateTime(DateUtils.getNowDate());
        tbVipGrade.setCreateBy(ShiroUtils.getLoginName());
        return tbVipGradeMapper.insertTbVipGrade(tbVipGrade);
    }

    /**
     * 修改会员等级管理
     * 
     * @param tbVipGrade 会员等级管理
     * @return 结果
     */
    @Override
    public int updateTbVipGrade(TbVipGrade tbVipGrade)
    {
        tbVipGrade.setUpdateTime(DateUtils.getNowDate());
         tbVipGrade.setUpdateBy(ShiroUtils.getLoginName());
        return tbVipGradeMapper.updateTbVipGrade(tbVipGrade);
    }

    /**
     * 批量删除会员等级管理
     * 
     * @param ids 需要删除的会员等级管理主键
     * @return 结果
     */
    @Override
    public int deleteTbVipGradeByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbVipGradeMapper.deleteTbVipGradeByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除会员等级管理信息
     * 
     * @param id 会员等级管理主键
     * @return 结果
     */
    @Override
    public int deleteTbVipGradeById(Long id)
    {
        return tbVipGradeMapper.deleteTbVipGradeById(id);
    }
}
