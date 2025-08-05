package com.tenant.business.service.impl;

import java.util.List;
import java.util.Map;

import com.tenant.business.domain.statistics.PersonWinRecord;
import com.tenant.business.domain.statistics.ScoreSummaryRecord;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbScoreRecordMapper;
import com.tenant.business.domain.TbScoreRecord;
import com.tenant.business.service.ITbScoreRecordService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 投注记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbScoreRecordServiceImpl implements ITbScoreRecordService 
{
    @Autowired
    private TbScoreRecordMapper tbScoreRecordMapper;

    /**
     * 查询投注记录
     * 
     * @param id 投注记录主键
     * @return 投注记录
     */
    @Override
    public TbScoreRecord selectTbScoreRecordById(Long id)
    {
        return tbScoreRecordMapper.selectTbScoreRecordById(id);
    }

    /**
     * 查询投注记录列表
     * 
     * @param tbScoreRecord 投注记录
     * @return 投注记录
     */
    @Override
    public List<TbScoreRecord> selectTbScoreRecordList(TbScoreRecord tbScoreRecord)
    {
        return tbScoreRecordMapper.selectTbScoreRecordList(tbScoreRecord);
    }

    /**
     * 新增投注记录
     * 
     * @param tbScoreRecord 投注记录
     * @return 结果
     */
    @Override
    public int insertTbScoreRecord(TbScoreRecord tbScoreRecord)
    {
        tbScoreRecord.setCreateTime(DateUtils.getNowDate());
        return tbScoreRecordMapper.insertTbScoreRecord(tbScoreRecord);
    }

    /**
     * 修改投注记录
     * 
     * @param tbScoreRecord 投注记录
     * @return 结果
     */
    @Override
    public int updateTbScoreRecord(TbScoreRecord tbScoreRecord)
    {
        tbScoreRecord.setUpdateTime(DateUtils.getNowDate());
        return tbScoreRecordMapper.updateTbScoreRecord(tbScoreRecord);
    }

    /**
     * 批量删除投注记录
     * 
     * @param ids 需要删除的投注记录主键
     * @return 结果
     */
    @Override
    public int deleteTbScoreRecordByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbScoreRecordMapper.deleteTbScoreRecordByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除投注记录信息
     * 
     * @param id 投注记录主键
     * @return 结果
     */
    @Override
    public int deleteTbScoreRecordById(Long id)
    {
        return tbScoreRecordMapper.deleteTbScoreRecordById(id);
    }

    @Override
    public int deleteByRange(String startTime, String endTime, long tenantId) {
        return tbScoreRecordMapper.deleteByRange(startTime,endTime,tenantId);
    }

    @Override
    public Map<String,Object> statisticsDashboardRecord(TbScoreRecord tbScoreRecord) {
        return tbScoreRecordMapper.statisticsDashboardRecord(tbScoreRecord);
    }
    @Override
    public List<ScoreSummaryRecord> scoreSummaryList(TbScoreRecord tbScoreRecord){
        return tbScoreRecordMapper.scoreSummaryList(tbScoreRecord);
    }

    @Override
    public int insertWinReport(String date){
        return tbScoreRecordMapper.insertWinReport(date);
    }

    @Override
    public Map<String,Object> statisticsPersonRecord(TbScoreRecord tbScoreRecord){
        return tbScoreRecordMapper.statisticsPersonRecord(tbScoreRecord);
    }

    @Override
    public List<PersonWinRecord> personWinRecord(TbScoreRecord tbScoreRecord){
        return tbScoreRecordMapper.personWinRecord(tbScoreRecord);
    }

    @Override
    public int insertHuiHongOrder(String date, double hh1, double hh2, double hh3, Long tenantId){
        return tbScoreRecordMapper.insertHuiHongOrder(date,hh1,hh2,hh3,tenantId);
    }

    @Override
    public int updateHuiHongUser(String date, double hh1, double hh2, double hh3,Long tenantId){
        return tbScoreRecordMapper.updateHuiHongUser(date,hh1,hh2,hh3,tenantId);
    }

    @Override
    public int insertLiuShuiOrder(String date, double ls1, double ls2, double ls3,Long tenantId){
        return tbScoreRecordMapper.insertLiuShuiOrder(date,ls1,ls2,ls3,tenantId);
    }

    @Override
    public int updateLiuShuiUser(String date, double ls1, double ls2, double ls3,Long tenantId){
        return tbScoreRecordMapper.updateLiuShuiUser(date,ls1,ls2,ls3,tenantId);
    }

    @Override
    public int insertBaShuOrder(String date, double bs1, double bs2, double bs3,Long tenantId){
        return tbScoreRecordMapper.insertBaShuOrder(date,bs1,bs2,bs3,tenantId);
    }

    @Override
    public int updateBaShuUser(String date, double bs1, double bs2, double bs3,Long tenantId){
        return tbScoreRecordMapper.updateBaShuUser(date,bs1,bs2,bs3,tenantId);
    }

    @Override
    public TbScoreRecord selectByIp(TbScoreRecord record) {
        return tbScoreRecordMapper.selectByIp(record);
    }
}
