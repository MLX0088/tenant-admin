package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbDrawManual;

/**
 * 手动开奖记录Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbDrawManualService 
{
    /**
     * 查询手动开奖记录
     * 
     * @param id 手动开奖记录主键
     * @return 手动开奖记录
     */
    public TbDrawManual selectTbDrawManualById(Long id);

    /**
     * 查询手动开奖记录列表
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 手动开奖记录集合
     */
    public List<TbDrawManual> selectTbDrawManualList(TbDrawManual tbDrawManual);

    /**
     * 新增手动开奖记录
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 结果
     */
    public int insertTbDrawManual(TbDrawManual tbDrawManual);

    /**
     * 修改手动开奖记录
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 结果
     */
    public int updateTbDrawManual(TbDrawManual tbDrawManual);

    /**
     * 批量删除手动开奖记录
     * 
     * @param ids 需要删除的手动开奖记录主键集合
     * @return 结果
     */
    public int deleteTbDrawManualByIds(String ids);

    /**
     * 删除手动开奖记录信息
     * 
     * @param id 手动开奖记录主键
     * @return 结果
     */
    public int deleteTbDrawManualById(Long id);
}
