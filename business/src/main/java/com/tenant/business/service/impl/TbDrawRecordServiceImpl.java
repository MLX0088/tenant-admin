package com.tenant.business.service.impl;

import java.util.List;

import com.tenant.business.domain.statistics.DrawWinRecord;
import com.tenant.business.domain.vo.DrawDashboardVo;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbDrawRecordMapper;
import com.tenant.business.domain.TbDrawRecord;
import com.tenant.business.service.ITbDrawRecordService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 开奖记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-04
 */
@Service
public class TbDrawRecordServiceImpl implements ITbDrawRecordService 
{
    @Autowired
    private TbDrawRecordMapper tbDrawRecordMapper;

    /**
     * 查询开奖记录
     * 
     * @param id 开奖记录主键
     * @return 开奖记录
     */
    @Override
    public TbDrawRecord selectTbDrawRecordById(Long id)
    {
        return tbDrawRecordMapper.selectTbDrawRecordById(id);
    }
    @Override
    public TbDrawRecord selectTbDrawRecordByNo(String no){
        return tbDrawRecordMapper.selectTbDrawRecordByNo(no);
    }
    @Override
    public TbDrawRecord selectLastTbDrawRecordByGameType(int gameType){
        return tbDrawRecordMapper.selectLastTbDrawRecordByGameType(gameType);
    }

    /**
     * 查询开奖记录列表
     * 
     * @param tbDrawRecord 开奖记录
     * @return 开奖记录
     */
    @Override
    public List<TbDrawRecord> selectTbDrawRecordList(TbDrawRecord tbDrawRecord)
    {

        return tbDrawRecordMapper.selectTbDrawRecordList(tbDrawRecord);
    }
    @Override
    public List<DrawDashboardVo> selectListForDashboard(TbDrawRecord tbDrawRecord){
        return tbDrawRecordMapper.selectListForDashboard(tbDrawRecord);
    }
    /**
     * 新增开奖记录
     * 
     * @param tbDrawRecord 开奖记录
     * @return 结果
     */
    @Override
    public int insertTbDrawRecord(TbDrawRecord tbDrawRecord)
    {
        tbDrawRecord.setCreateTime(DateUtils.getNowDate());
        return tbDrawRecordMapper.insertTbDrawRecord(tbDrawRecord);
    }

    /**
     * 修改开奖记录
     * 
     * @param tbDrawRecord 开奖记录
     * @return 结果
     */
    @Override
    public int updateTbDrawRecord(TbDrawRecord tbDrawRecord)
    {
        tbDrawRecord.setUpdateTime(DateUtils.getNowDate());
        return tbDrawRecordMapper.updateTbDrawRecord(tbDrawRecord);
    }

    /**
     * 批量删除开奖记录
     * 
     * @param ids 需要删除的开奖记录主键
     * @return 结果
     */
    @Override
    public int deleteTbDrawRecordByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbDrawRecordMapper.deleteTbDrawRecordByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除开奖记录信息
     * 
     * @param id 开奖记录主键
     * @return 结果
     */
    @Override
    public int deleteTbDrawRecordById(Long id)
    {
        return tbDrawRecordMapper.deleteTbDrawRecordById(id);
    }

    @Override
    public int deleteByRange(String startTime, String endTime, long tenantId) {
        return tbDrawRecordMapper.deleteByRange(startTime,endTime,tenantId);
    }

    @Override
    public List<DrawWinRecord> drawWinList(TbDrawRecord tbDrawRecord){
        return tbDrawRecordMapper.drawWinList(tbDrawRecord);
    }

    @Override
    public TbDrawRecord currentDrawRecord(Long id){
        return tbDrawRecordMapper.currentDrawRecord(id);
    }
}
