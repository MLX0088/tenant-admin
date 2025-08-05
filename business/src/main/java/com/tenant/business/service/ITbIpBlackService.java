package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbIpBlack;

/**
 * IP黑名单Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbIpBlackService 
{
    /**
     * 查询IP黑名单
     * 
     * @param id IP黑名单主键
     * @return IP黑名单
     */
    public TbIpBlack selectTbIpBlackById(Long id);

    /**
     * 查询IP黑名单列表
     * 
     * @param tbIpBlack IP黑名单
     * @return IP黑名单集合
     */
    public List<TbIpBlack> selectTbIpBlackList(TbIpBlack tbIpBlack);

    /**
     * 新增IP黑名单
     * 
     * @param tbIpBlack IP黑名单
     * @return 结果
     */
    public int insertTbIpBlack(TbIpBlack tbIpBlack);

    /**
     * 修改IP黑名单
     * 
     * @param tbIpBlack IP黑名单
     * @return 结果
     */
    public int updateTbIpBlack(TbIpBlack tbIpBlack);

    /**
     * 批量删除IP黑名单
     * 
     * @param ids 需要删除的IP黑名单主键集合
     * @return 结果
     */
    public int deleteTbIpBlackByIds(String ids);

    /**
     * 删除IP黑名单信息
     * 
     * @param id IP黑名单主键
     * @return 结果
     */
    public int deleteTbIpBlackById(Long id);
}
