package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbTurntableRule;

/**
 * 转盘管理Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbTurntableRuleService 
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
     * 批量删除转盘管理
     * 
     * @param ids 需要删除的转盘管理主键集合
     * @return 结果
     */
    public int deleteTbTurntableRuleByIds(String ids);

    /**
     * 删除转盘管理信息
     * 
     * @param id 转盘管理主键
     * @return 结果
     */
    public int deleteTbTurntableRuleById(Long id);
}
