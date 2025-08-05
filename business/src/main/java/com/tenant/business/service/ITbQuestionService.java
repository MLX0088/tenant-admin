package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbQuestion;

/**
 * 问题管理Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbQuestionService 
{
    /**
     * 查询问题管理
     * 
     * @param id 问题管理主键
     * @return 问题管理
     */
    public TbQuestion selectTbQuestionById(Long id);

    /**
     * 查询问题管理列表
     * 
     * @param tbQuestion 问题管理
     * @return 问题管理集合
     */
    public List<TbQuestion> selectTbQuestionList(TbQuestion tbQuestion);

    /**
     * 新增问题管理
     * 
     * @param tbQuestion 问题管理
     * @return 结果
     */
    public int insertTbQuestion(TbQuestion tbQuestion);

    /**
     * 修改问题管理
     * 
     * @param tbQuestion 问题管理
     * @return 结果
     */
    public int updateTbQuestion(TbQuestion tbQuestion);

    /**
     * 批量删除问题管理
     * 
     * @param ids 需要删除的问题管理主键集合
     * @return 结果
     */
    public int deleteTbQuestionByIds(String ids);

    /**
     * 删除问题管理信息
     * 
     * @param id 问题管理主键
     * @return 结果
     */
    public int deleteTbQuestionById(Long id);
}
