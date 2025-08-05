package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbTurntableRule;
import org.apache.ibatis.annotations.Param;

/**
 * 转盘管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbTurntableRuleMapper 
{
    /**
     * 查询转盘管理
     * 
     * @param id 转盘管理主键
     * @return 转盘管理
     */
    public TbTurntableRule selectTbTurntableRuleById(Long id);

    /**
     * 查询转盘管理列表
     * 
     * @param tbTurntableRule 转盘管理
     * @return 转盘管理集合
     */
    public List<TbTurntableRule> selectTbTurntableRuleList(TbTurntableRule tbTurntableRule);

    /**
     * 新增转盘管理
     * 
     * @param tbTurntableRule 转盘管理
     * @return 结果
     */
    public int insertTbTurntableRule(TbTurntableRule tbTurntableRule);

    /**
     * 修改转盘管理
     * 
     * @param tbTurntableRule 转盘管理
     * @return 结果
     */
    public int updateTbTurntableRule(TbTurntableRule tbTurntableRule);

    /**
     * 删除转盘管理
     * 
     * @param id 转盘管理主键
     * @return 结果
     */
    public int deleteTbTurntableRuleById(Long id);

    /**
     * 批量删除转盘管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbTurntableRuleByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
