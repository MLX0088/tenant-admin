package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbQuestion;
import org.apache.ibatis.annotations.Param;

/**
 * 问题管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbQuestionMapper 
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
     * 删除问题管理
     * 
     * @param id 问题管理主键
     * @return 结果
     */
    public int deleteTbQuestionById(Long id);

    /**
     * 批量删除问题管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbQuestionByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
