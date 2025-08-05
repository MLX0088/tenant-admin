package com.tenant.business.mapper;

import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbOrderRecord;
import com.tenant.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 订单记录Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbOrderRecordMapper 
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

    /**
     * 新增订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    public int insertTbOrderRecord(TbOrderRecord tbOrderRecord);
    public int availableCount(TbOrderRecord tbOrderRecord);

    /**
     * 修改订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    public int updateTbOrderRecord(TbOrderRecord tbOrderRecord);

    /**
     * 删除订单记录
     * 
     * @param id 订单记录主键
     * @return 结果
     */
    public int deleteTbOrderRecordById(Long id);
    public int deleteByRange(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tenantId") long tenantId);

    /**
     * 批量删除订单记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbOrderRecordByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);


    public List<TbOrderRecord> selectUpList(TbOrderRecord tbOrderRecord);
    public List<TbOrderRecord> selectDownList(TbOrderRecord tbOrderRecord);

    public Map<String,Object> statisticsDashboardRecord(TbOrderRecord tbOrderRecord);

    public double auditScore(TbOrderRecord tbOrderRecord);

    public double todayLiushui(TbOrderRecord tbOrderRecord);

    public int todayLuckyDrawCount(TbOrderRecord tbOrderRecord);

    public List<TbOrderRecord> selectTbOrderRecordListForApp(TbOrderRecord tbOrderRecord);
}
