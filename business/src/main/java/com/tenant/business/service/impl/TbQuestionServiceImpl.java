package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbQuestionMapper;
import com.tenant.business.domain.TbQuestion;
import com.tenant.business.service.ITbQuestionService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 问题管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbQuestionServiceImpl implements ITbQuestionService 
{
    @Autowired
    private TbQuestionMapper tbQuestionMapper;

    /**
     * 查询问题管理
     * 
     * @param id 问题管理主键
     * @return 问题管理
     */
    @Override
    public TbQuestion selectTbQuestionById(Long id)
    {
        return tbQuestionMapper.selectTbQuestionById(id);
    }

    /**
     * 查询问题管理列表
     * 
     * @param tbQuestion 问题管理
     * @return 问题管理
     */
    @Override
    public List<TbQuestion> selectTbQuestionList(TbQuestion tbQuestion)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbQuestion.setCreateBy(sysUser.getLoginName());
        }
        return tbQuestionMapper.selectTbQuestionList(tbQuestion);
    }

    /**
     * 新增问题管理
     * 
     * @param tbQuestion 问题管理
     * @return 结果
     */
    @Override
    public int insertTbQuestion(TbQuestion tbQuestion)
    {
        tbQuestion.setCreateTime(DateUtils.getNowDate());
        tbQuestion.setCreateBy(ShiroUtils.getLoginName());
        return tbQuestionMapper.insertTbQuestion(tbQuestion);
    }

    /**
     * 修改问题管理
     * 
     * @param tbQuestion 问题管理
     * @return 结果
     */
    @Override
    public int updateTbQuestion(TbQuestion tbQuestion)
    {
        tbQuestion.setUpdateTime(DateUtils.getNowDate());
         tbQuestion.setUpdateBy(ShiroUtils.getLoginName());
        return tbQuestionMapper.updateTbQuestion(tbQuestion);
    }

    /**
     * 批量删除问题管理
     * 
     * @param ids 需要删除的问题管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuestionByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbQuestionMapper.deleteTbQuestionByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除问题管理信息
     * 
     * @param id 问题管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuestionById(Long id)
    {
        return tbQuestionMapper.deleteTbQuestionById(id);
    }
}
