package com.tenant.business.mapper;

import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbScoreRecord;
import com.tenant.business.domain.statistics.PersonWinRecord;
import com.tenant.business.domain.statistics.ScoreSummaryRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 投注记录Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbScoreRecordMapper 
{
    /**
     * 查询投注记录
     * 
     * @param id 投注记录主键
     * @return 投注记录
     */
    public TbScoreRecord selectTbScoreRecordById(Long id);

    /**
     * 查询投注记录列表
     * 
     * @param tbScoreRecord 投注记录
     * @return 投注记录集合
     */
    public List<TbScoreRecord> selectTbScoreRecordList(TbScoreRecord tbScoreRecord);

    /**
     * 新增投注记录
     * 
     * @param tbScoreRecord 投注记录
     * @return 结果
     */
    public int insertTbScoreRecord(TbScoreRecord tbScoreRecord);

    /**
     * 修改投注记录
     * 
     * @param tbScoreRecord 投注记录
     * @return 结果
     */
    public int updateTbScoreRecord(TbScoreRecord tbScoreRecord);

    /**
     * 删除投注记录
     * 
     * @param id 投注记录主键
     * @return 结果
     */
    public int deleteTbScoreRecordById(Long id);
    public int deleteByRange(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tenantId") long tenantId);

    /**
     * 批量删除投注记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbScoreRecordByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);

    public Map<String,Object> statisticsDashboardRecord(TbScoreRecord tbScoreRecord);
    public Map<String,Object> statisticsPersonRecord(TbScoreRecord tbScoreRecord);

    public List<ScoreSummaryRecord> scoreSummaryList(TbScoreRecord tbScoreRecord);

    public int insertWinReport(String date);

    List<PersonWinRecord> personWinRecord(TbScoreRecord tbScoreRecord);

    public int insertHuiHongOrder(@Param("date") String date,@Param("hh1") double hh1,@Param("hh2") double hh2,@Param("hh3") double hh3,@Param("tenantId") Long tenantId);

    public int updateHuiHongUser(@Param("date") String date,@Param("hh1") double hh1,@Param("hh2") double hh2,@Param("hh3") double hh3,@Param("tenantId") Long tenantId);

    public int insertLiuShuiOrder(@Param("date") String date,@Param("ls1") double ls1,@Param("ls2") double ls2,@Param("ls3") double ls3,@Param("tenantId") Long tenantId);

    public int updateLiuShuiUser(@Param("date") String date,@Param("ls1") double ls1,@Param("ls2") double ls2,@Param("ls3") double ls3,@Param("tenantId") Long tenantId);

    public int insertBaShuOrder(@Param("date") String date,@Param("bs1") double bs1,@Param("bs2") double bs2,@Param("bs3") double bs3,@Param("tenantId") Long tenantId);

    public int updateBaShuUser(@Param("date") String date,@Param("bs1") double bs1,@Param("bs2") double bs2,@Param("bs3") double bs3,@Param("tenantId") Long tenantId);

    public TbScoreRecord selectByIp(TbScoreRecord record);
}
