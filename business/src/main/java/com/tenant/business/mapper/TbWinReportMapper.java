package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbWinReport;
import org.apache.ibatis.annotations.Param;

/**
 * 盈亏信息Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-05
 */
public interface TbWinReportMapper 
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
     * 删除盈亏信息
     * 
     * @param id 盈亏信息主键
     * @return 结果
     */
    public int deleteTbWinReportById(Long id);
    public int deleteByRange(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tenantId") long tenantId);

    /**
     * 批量删除盈亏信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbWinReportByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);


    public int genWinDataDaily(String date);
    public int getDataCount(String date);
}
