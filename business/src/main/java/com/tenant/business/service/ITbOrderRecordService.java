package com.tenant.business.service;

import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbOrderRecord;

/**
 * 订单记录Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbOrderRecordService 
{
    /**
     * 查询订单记录
     * 
     * @param id 订单记录主键
     * @return 订单记录
     */
    public TbOrderRecord selectTbOrderRecordById(Long id);

    /**
     * 查询订单记录列表
     * 
     * @param tbOrderRecord 订单记录
     * @return 订单记录集合
     */
    public List<TbOrderRecord> selectTbOrderRecordList(TbOrderRecord tbOrderRecord);
    public List<TbOrderRecord> selectUpList(TbOrderRecord tbOrderRecord);
    public List<TbOrderRecord> selectDownList(TbOrderRecord tbOrderRecord);

    /**
     * 新增订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    public int insertTbOrderRecord(TbOrderRecord tbOrderRecord);

    /**
     * 修改订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    public int updateTbOrderRecord(TbOrderRecord tbOrderRecord);

    /**
     * 批量删除订单记录
     * 
     * @param ids 需要删除的订单记录主键集合
     * @return 结果
     */
    public int deleteTbOrderRecordByIds(String ids);

    /**
     * 删除订单记录信息
     * 
     * @param id 订单记录主键
     * @return 结果
     */
    public int deleteTbOrderRecordById(Long id);
    public int deleteByRange(String startTime, String endTime, long tenantId);

    public Map<String,Object> statisticsDashboardRecord(TbOrderRecord tbOrderRecord);

    List<TbOrderRecord> selectTbOrderRecordListForApp(TbOrderRecord tbOrderRecord);

    int availableCount(TbOrderRecord tbOrderRecord);

    public double auditScore(TbOrderRecord tbOrderRecord);

    double todayLiushui(TbOrderRecord tbOrderRecord);

    int todayLuckyDrawCount(TbOrderRecord tbOrderRecord);
}
