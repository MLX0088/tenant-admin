package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbDrawRecord;
import com.tenant.business.domain.statistics.DrawWinRecord;
import com.tenant.business.domain.vo.DrawDashboardVo;
import org.apache.ibatis.annotations.Param;

/**
 * 开奖记录Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-04
 */
public interface TbDrawRecordMapper 
{
    /**
     * 查询开奖记录
     * 
     * @param id 开奖记录主键
     * @return 开奖记录
     */
    public TbDrawRecord selectTbDrawRecordById(Long id);
    public TbDrawRecord currentDrawRecord(Long id);
    public TbDrawRecord selectTbDrawRecordByNo(String no);
    public TbDrawRecord selectLastTbDrawRecordByGameType(int gameType);
    /**
     * 查询开奖记录列表
     * 
     * @param tbDrawRecord 开奖记录
     * @return 开奖记录集合
     */
    public List<TbDrawRecord> selectTbDrawRecordList(TbDrawRecord tbDrawRecord);

    public List<DrawDashboardVo> selectListForDashboard(TbDrawRecord tbDrawRecord);
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
     * 删除开奖记录
     * 
     * @param id 开奖记录主键
     * @return 结果
     */
    public int deleteTbDrawRecordById(Long id);

    public int deleteByRange(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tenantId") long tenantId);
    /**
     * 批量删除开奖记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbDrawRecordByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);

    public List<DrawWinRecord> drawWinList(TbDrawRecord tbDrawRecord);
}
