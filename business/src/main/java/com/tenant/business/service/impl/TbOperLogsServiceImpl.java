package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbOperLogsMapper;
import com.tenant.business.domain.TbOperLogs;
import com.tenant.business.service.ITbOperLogsService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 操作记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbOperLogsServiceImpl implements ITbOperLogsService 
{
    @Autowired
    private TbOperLogsMapper tbOperLogsMapper;

    /**
     * 查询操作记录
     * 
     * @param id 操作记录主键
     * @return 操作记录
     */
    @Override
    public TbOperLogs selectTbOperLogsById(Long id)
    {
        return tbOperLogsMapper.selectTbOperLogsById(id);
    }

    /**
     * 查询操作记录列表
     * 
     * @param tbOperLogs 操作记录
     * @return 操作记录
     */
    @Override
    public List<TbOperLogs> selectTbOperLogsList(TbOperLogs tbOperLogs)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbOperLogs.setCreateBy(sysUser.getLoginName());
        }
        return tbOperLogsMapper.selectTbOperLogsList(tbOperLogs);
    }

    /**
     * 新增操作记录
     * 
     * @param tbOperLogs 操作记录
     * @return 结果
     */
    @Override
    public int insertTbOperLogs(TbOperLogs tbOperLogs)
    {
        tbOperLogs.setCreateTime(DateUtils.getNowDate());
        tbOperLogs.setCreateBy(ShiroUtils.getLoginName());
        return tbOperLogsMapper.insertTbOperLogs(tbOperLogs);
    }

    /**
     * 修改操作记录
     * 
     * @param tbOperLogs 操作记录
     * @return 结果
     */
    @Override
    public int updateTbOperLogs(TbOperLogs tbOperLogs)
    {
        tbOperLogs.setUpdateTime(DateUtils.getNowDate());
         tbOperLogs.setUpdateBy(ShiroUtils.getLoginName());
        return tbOperLogsMapper.updateTbOperLogs(tbOperLogs);
    }

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的操作记录主键
     * @return 结果
     */
    @Override
    public int deleteTbOperLogsByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbOperLogsMapper.deleteTbOperLogsByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除操作记录信息
     * 
     * @param id 操作记录主键
     * @return 结果
     */
    @Override
    public int deleteTbOperLogsById(Long id)
    {
        return tbOperLogsMapper.deleteTbOperLogsById(id);
    }
}
