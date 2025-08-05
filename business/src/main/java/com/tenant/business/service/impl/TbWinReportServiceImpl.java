package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbWinReportMapper;
import com.tenant.business.domain.TbWinReport;
import com.tenant.business.service.ITbWinReportService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 盈亏信息Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-05
 */
@Service
public class TbWinReportServiceImpl implements ITbWinReportService 
{
    @Autowired
    private TbWinReportMapper tbWinReportMapper;

    /**
     * 查询盈亏信息
     * 
     * @param id 盈亏信息主键
     * @return 盈亏信息
     */
    @Override
    public TbWinReport selectTbWinReportById(Long id)
    {
        return tbWinReportMapper.selectTbWinReportById(id);
    }

    /**
     * 查询盈亏信息列表
     * 
     * @param tbWinReport 盈亏信息
     * @return 盈亏信息
     */
    @Override
    public List<TbWinReport> selectTbWinReportList(TbWinReport tbWinReport)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbWinReport.setCreateBy(sysUser.getLoginName());
        }
        return tbWinReportMapper.selectTbWinReportList(tbWinReport);
    }

    /**
     * 新增盈亏信息
     * 
     * @param tbWinReport 盈亏信息
     * @return 结果
     */
    @Override
    public int insertTbWinReport(TbWinReport tbWinReport)
    {
        tbWinReport.setCreateTime(DateUtils.getNowDate());
        tbWinReport.setCreateBy(ShiroUtils.getLoginName());
        return tbWinReportMapper.insertTbWinReport(tbWinReport);
    }

    /**
     * 修改盈亏信息
     * 
     * @param tbWinReport 盈亏信息
     * @return 结果
     */
    @Override
    public int updateTbWinReport(TbWinReport tbWinReport)
    {
        tbWinReport.setUpdateTime(DateUtils.getNowDate());
         tbWinReport.setUpdateBy(ShiroUtils.getLoginName());
        return tbWinReportMapper.updateTbWinReport(tbWinReport);
    }

    /**
     * 批量删除盈亏信息
     * 
     * @param ids 需要删除的盈亏信息主键
     * @return 结果
     */
    @Override
    public int deleteTbWinReportByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbWinReportMapper.deleteTbWinReportByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除盈亏信息信息
     * 
     * @param id 盈亏信息主键
     * @return 结果
     */
    @Override
    public int deleteTbWinReportById(Long id)
    {
        return tbWinReportMapper.deleteTbWinReportById(id);
    }

    @Override
    public int deleteByRange(String startTime, String endTime, long tenantId) {
        return tbWinReportMapper.deleteByRange(startTime,endTime,tenantId);
    }

    @Override
    public int genWinDataDaily(String date){
        return tbWinReportMapper.genWinDataDaily(date);
    }

    @Override
    public int getDataCount(String date){
        return tbWinReportMapper.getDataCount(date);
    }

}
