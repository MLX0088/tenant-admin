package com.tenant.business.service.impl;

import java.util.List;
import java.util.Map;

import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbOrderRecordMapper;
import com.tenant.business.domain.TbOrderRecord;
import com.tenant.business.service.ITbOrderRecordService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 订单记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbOrderRecordServiceImpl implements ITbOrderRecordService 
{
    @Autowired
    private TbOrderRecordMapper tbOrderRecordMapper;

    /**
     * 查询订单记录
     * 
     * @param id 订单记录主键
     * @return 订单记录
     */
    @Override
    public TbOrderRecord selectTbOrderRecordById(Long id)
    {
        return tbOrderRecordMapper.selectTbOrderRecordById(id);
    }

    /**
     * 查询订单记录列表
     * 
     * @param tbOrderRecord 订单记录
     * @return 订单记录
     */
    @Override
    public List<TbOrderRecord> selectTbOrderRecordList(TbOrderRecord tbOrderRecord)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbOrderRecord.setCreateBy(sysUser.getLoginName());
        }
        return tbOrderRecordMapper.selectTbOrderRecordList(tbOrderRecord);
    }

    public List<TbOrderRecord> selectUpList(TbOrderRecord tbOrderRecord) {
        return tbOrderRecordMapper.selectUpList(tbOrderRecord);
    }
    public List<TbOrderRecord> selectDownList(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.selectDownList(tbOrderRecord);
    }

    /**
     * 新增订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    @Override
    public int insertTbOrderRecord(TbOrderRecord tbOrderRecord)
    {
        if(tbOrderRecord.getCreateTime() == null){
            tbOrderRecord.setCreateTime(DateUtils.getNowDate());
        }
        tbOrderRecord.setCreateBy(ShiroUtils.getLoginName());
        return tbOrderRecordMapper.insertTbOrderRecord(tbOrderRecord);
    }

    /**
     * 修改订单记录
     * 
     * @param tbOrderRecord 订单记录
     * @return 结果
     */
    @Override
    public int updateTbOrderRecord(TbOrderRecord tbOrderRecord)
    {
        tbOrderRecord.setUpdateTime(DateUtils.getNowDate());
         tbOrderRecord.setUpdateBy(ShiroUtils.getLoginName());
        return tbOrderRecordMapper.updateTbOrderRecord(tbOrderRecord);
    }

    /**
     * 批量删除订单记录
     * 
     * @param ids 需要删除的订单记录主键
     * @return 结果
     */
    @Override
    public int deleteTbOrderRecordByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbOrderRecordMapper.deleteTbOrderRecordByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除订单记录信息
     * 
     * @param id 订单记录主键
     * @return 结果
     */
    @Override
    public int deleteTbOrderRecordById(Long id)
    {
        return tbOrderRecordMapper.deleteTbOrderRecordById(id);
    }

    @Override
    public int deleteByRange(String startTime, String endTime, long tenantId) {
        return tbOrderRecordMapper.deleteByRange(startTime,endTime,tenantId);
    }

    public Map<String,Object> statisticsDashboardRecord(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.statisticsDashboardRecord(tbOrderRecord);
    }

    @Override
    public List<TbOrderRecord> selectTbOrderRecordListForApp(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.selectTbOrderRecordListForApp(tbOrderRecord);
    }

    @Override
    public int availableCount(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.availableCount(tbOrderRecord);
    }

    public double auditScore(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.auditScore(tbOrderRecord);
    }

    @Override
    public double todayLiushui(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.todayLiushui(tbOrderRecord);
    }

    @Override
    public int todayLuckyDrawCount(TbOrderRecord tbOrderRecord){
        return tbOrderRecordMapper.todayLuckyDrawCount(tbOrderRecord);
    }
}
