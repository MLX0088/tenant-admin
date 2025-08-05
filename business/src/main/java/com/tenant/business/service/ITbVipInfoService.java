package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbVipInfo;

/**
 * 会员信息Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbVipInfoService 
{
    /**
     * 查询会员信息
     * 
     * @param id 会员信息主键
     * @return 会员信息
     */
    public TbVipInfo selectTbVipInfoById(Long id);

    /**
     * 查询会员信息列表
     * 
     * @param tbVipInfo 会员信息
     * @return 会员信息集合
     */
    public List<TbVipInfo> selectTbVipInfoList(TbVipInfo tbVipInfo);

    /**
     * 新增会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    public int insertTbVipInfo(TbVipInfo tbVipInfo);

    /**
     * 修改会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    public int updateTbVipInfo(TbVipInfo tbVipInfo);

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的会员信息主键集合
     * @return 结果
     */
    public int deleteTbVipInfoByIds(String ids);

    /**
     * 删除会员信息信息
     * 
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteTbVipInfoById(Long id);
}
