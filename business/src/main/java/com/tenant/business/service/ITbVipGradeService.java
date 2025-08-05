package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbVipGrade;

/**
 * 会员等级管理Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbVipGradeService 
{
    /**
     * 查询会员等级管理
     * 
     * @param id 会员等级管理主键
     * @return 会员等级管理
     */
    public TbVipGrade selectTbVipGradeById(Long id);

    /**
     * 查询会员等级管理列表
     * 
     * @param tbVipGrade 会员等级管理
     * @return 会员等级管理集合
     */
    public List<TbVipGrade> selectTbVipGradeList(TbVipGrade tbVipGrade);

    /**
     * 新增会员等级管理
     * 
     * @param tbVipGrade 会员等级管理
     * @return 结果
     */
    public int insertTbVipGrade(TbVipGrade tbVipGrade);

    /**
     * 修改会员等级管理
     * 
     * @param tbVipGrade 会员等级管理
     * @return 结果
     */
    public int updateTbVipGrade(TbVipGrade tbVipGrade);

    /**
     * 批量删除会员等级管理
     * 
     * @param ids 需要删除的会员等级管理主键集合
     * @return 结果
     */
    public int deleteTbVipGradeByIds(String ids);

    /**
     * 删除会员等级管理信息
     * 
     * @param id 会员等级管理主键
     * @return 结果
     */
    public int deleteTbVipGradeById(Long id);
}
