package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbDrawManualMapper;
import com.tenant.business.domain.TbDrawManual;
import com.tenant.business.service.ITbDrawManualService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 手动开奖记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbDrawManualServiceImpl implements ITbDrawManualService 
{
    @Autowired
    private TbDrawManualMapper tbDrawManualMapper;

    /**
     * 查询手动开奖记录
     * 
     * @param id 手动开奖记录主键
     * @return 手动开奖记录
     */
    @Override
    public TbDrawManual selectTbDrawManualById(Long id)
    {
        return tbDrawManualMapper.selectTbDrawManualById(id);
    }

    /**
     * 查询手动开奖记录列表
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 手动开奖记录
     */
    @Override
    public List<TbDrawManual> selectTbDrawManualList(TbDrawManual tbDrawManual)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbDrawManual.setCreateBy(sysUser.getLoginName());
        }
        return tbDrawManualMapper.selectTbDrawManualList(tbDrawManual);
    }

    /**
     * 新增手动开奖记录
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 结果
     */
    @Override
    public int insertTbDrawManual(TbDrawManual tbDrawManual)
    {
        tbDrawManual.setCreateTime(DateUtils.getNowDate());
        tbDrawManual.setCreateBy(ShiroUtils.getLoginName());
        return tbDrawManualMapper.insertTbDrawManual(tbDrawManual);
    }

    /**
     * 修改手动开奖记录
     * 
     * @param tbDrawManual 手动开奖记录
     * @return 结果
     */
    @Override
    public int updateTbDrawManual(TbDrawManual tbDrawManual)
    {
        tbDrawManual.setUpdateTime(DateUtils.getNowDate());
         tbDrawManual.setUpdateBy(ShiroUtils.getLoginName());
        return tbDrawManualMapper.updateTbDrawManual(tbDrawManual);
    }

    /**
     * 批量删除手动开奖记录
     * 
     * @param ids 需要删除的手动开奖记录主键
     * @return 结果
     */
    @Override
    public int deleteTbDrawManualByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbDrawManualMapper.deleteTbDrawManualByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除手动开奖记录信息
     * 
     * @param id 手动开奖记录主键
     * @return 结果
     */
    @Override
    public int deleteTbDrawManualById(Long id)
    {
        return tbDrawManualMapper.deleteTbDrawManualById(id);
    }
}
