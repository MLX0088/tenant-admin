package com.tenant.business.service;

import java.util.List;
import java.util.Map;

import com.tenant.business.domain.TbScoreRecord;
import com.tenant.business.domain.statistics.PersonWinRecord;
import com.tenant.business.domain.statistics.ScoreSummaryRecord;

/**
 * 投注记录Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbScoreRecordService 
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
     * 批量删除投注记录
     * 
     * @param ids 需要删除的投注记录主键集合
     * @return 结果
     */
    public int deleteTbScoreRecordByIds(String ids);

    /**
     * 删除投注记录信息
     * 
     * @param id 投注记录主键
     * @return 结果
     */
    public int deleteTbScoreRecordById(Long id);
    public int deleteByRange(String startTime, String endTime, long tenantId);

    public Map<String,Object> statisticsDashboardRecord(TbScoreRecord tbScoreRecord);

    public List<ScoreSummaryRecord> scoreSummaryList(TbScoreRecord tbScoreRecord);

    int insertWinReport(String date);

    Map<String,Object> statisticsPersonRecord(TbScoreRecord tbScoreRecord);

    List<PersonWinRecord> personWinRecord(TbScoreRecord tbScoreRecord);

    int insertHuiHongOrder(String date, double hh1, double hh2, double hh3,Long tenantId);

    int updateHuiHongUser(String date, double hh1, double hh2, double hh3,Long tenantId);

    int insertLiuShuiOrder(String date, double ls1, double ls2, double ls3,Long tenantId);

    int updateLiuShuiUser(String date, double ls1, double ls2, double ls3,Long tenantId);

    int insertBaShuOrder(String date, double bs1, double bs2, double bs3,Long tenantId);

    int updateBaShuUser(String date, double bs1, double bs2, double bs3,Long tenantId);

    TbScoreRecord selectByIp(TbScoreRecord record);
}
