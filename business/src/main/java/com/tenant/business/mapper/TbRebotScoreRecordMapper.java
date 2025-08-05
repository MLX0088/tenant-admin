package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbRebotScoreRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 机器人投注记录Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbRebotScoreRecordMapper 
{
    /**
     * 查询机器人投注记录
     * 
     * @param id 机器人投注记录主键
     * @return 机器人投注记录
     */
    public TbRebotScoreRecord selectTbRebotScoreRecordById(Long id);

    /**
     * 查询机器人投注记录列表
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 机器人投注记录集合
     */
    public List<TbRebotScoreRecord> selectTbRebotScoreRecordList(TbRebotScoreRecord tbRebotScoreRecord);

    /**
     * 新增机器人投注记录
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 结果
     */
    public int insertTbRebotScoreRecord(TbRebotScoreRecord tbRebotScoreRecord);

    /**
     * 修改机器人投注记录
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 结果
     */
    public int updateTbRebotScoreRecord(TbRebotScoreRecord tbRebotScoreRecord);

    /**
     * 删除机器人投注记录
     * 
     * @param id 机器人投注记录主键
     * @return 结果
     */
    public int deleteTbRebotScoreRecordById(Long id);

    /**
     * 批量删除机器人投注记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbRebotScoreRecordByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
