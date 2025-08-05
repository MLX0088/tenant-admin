package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbDrawRecord;
import com.tenant.business.domain.statistics.DrawWinRecord;
import com.tenant.business.domain.vo.DrawDashboardVo;

/**
 * 开奖记录Service接口
 * 
 * @author luanyu
 * @date 2025-05-04
 */
public interface ITbDrawRecordService 
{
    /**
     * 查询开奖记录
     * 
     * @param id 开奖记录主键
     * @return 开奖记录
     */
    public TbDrawRecord selectTbDrawRecordById(Long id);
    public TbDrawRecord selectTbDrawRecordByNo(String no);

    TbDrawRecord selectLastTbDrawRecordByGameType(int gameType);

    /**
     * 查询开奖记录列表
     * 
     * @param tbDrawRecord 开奖记录
     * @return 开奖记录集合
     */
    public List<TbDrawRecord> selectTbDrawRecordList(TbDrawRecord tbDrawRecord);

    List<DrawDashboardVo> selectListForDashboard(TbDrawRecord tbDrawRecord);

    /**
     * 新增开奖记录
     * 
     * @param tbDrawRecord 开奖记录
     * @return 结果
     */
    public int insertTbDrawRecord(TbDrawRecord tbDrawRecord);

    /**
     * 修改开奖记录
     * 
     * @param tbDrawRecord 开奖记录
     * @return 结果
     */
    public int updateTbDrawRecord(TbDrawRecord tbDrawRecord);

    /**
     * 批量删除开奖记录
     * 
     * @param ids 需要删除的开奖记录主键集合
     * @return 结果
     */
    public int deleteTbDrawRecordByIds(String ids);

    /**
     * 删除开奖记录信息
     * 
     * @param id 开奖记录主键
     * @return 结果
     */
    public int deleteTbDrawRecordById(Long id);
    public int deleteByRange(String startTime, String endTime, long tenantId);

    public List<DrawWinRecord> drawWinList(TbDrawRecord tbDrawRecord);

    TbDrawRecord currentDrawRecord(Long id);
}
