package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbWinReport;
import org.apache.ibatis.annotations.Param;

/**
 * 盈亏信息Service接口
 * 
 * @author luanyu
 * @date 2025-05-05
 */
public interface ITbWinReportService 
{
    /**
     * 查询盈亏信息
     * 
     * @param id 盈亏信息主键
     * @return 盈亏信息
     */
    public TbWinReport selectTbWinReportById(Long id);

    /**
     * 查询盈亏信息列表
     * 
     * @param tbWinReport 盈亏信息
     * @return 盈亏信息集合
     */
    public List<TbWinReport> selectTbWinReportList(TbWinReport tbWinReport);

    /**
     * 新增盈亏信息
     * 
     * @param tbWinReport 盈亏信息
     * @return 结果
     */
    public int insertTbWinReport(TbWinReport tbWinReport);

    /**
     * 修改盈亏信息
     * 
     * @param tbWinReport 盈亏信息
     * @return 结果
     */
    public int updateTbWinReport(TbWinReport tbWinReport);

    /**
     * 批量删除盈亏信息
     * 
     * @param ids 需要删除的盈亏信息主键集合
     * @return 结果
     */
    public int deleteTbWinReportByIds(String ids);

    /**
     * 删除盈亏信息信息
     * 
     * @param id 盈亏信息主键
     * @return 结果
     */
    public int deleteTbWinReportById(Long id);

    public int deleteByRange(String startTime, String endTime, long tenantId);

    public int genWinDataDaily(String date);
    public int getDataCount(String date);
}
