package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbVipPayMapper;
import com.tenant.business.domain.TbVipPay;
import com.tenant.business.service.ITbVipPayService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 会员支付信息Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbVipPayServiceImpl implements ITbVipPayService 
{
    @Autowired
    private TbVipPayMapper tbVipPayMapper;

    /**
     * 查询会员支付信息
     * 
     * @param id 会员支付信息主键
     * @return 会员支付信息
     */
    @Override
    public TbVipPay selectTbVipPayById(Long id)
    {
        return tbVipPayMapper.selectTbVipPayById(id);
    }

    /**
     * 查询会员支付信息列表
     * 
     * @param tbVipPay 会员支付信息
     * @return 会员支付信息
     */
    @Override
    public List<TbVipPay> selectTbVipPayList(TbVipPay tbVipPay)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbVipPay.setCreateBy(sysUser.getLoginName());
        }
        return tbVipPayMapper.selectTbVipPayList(tbVipPay);
    }

    /**
     * 新增会员支付信息
     * 
     * @param tbVipPay 会员支付信息
     * @return 结果
     */
    @Override
    public int insertTbVipPay(TbVipPay tbVipPay)
    {
        tbVipPay.setCreateTime(DateUtils.getNowDate());
        tbVipPay.setCreateBy(ShiroUtils.getLoginName());
        return tbVipPayMapper.insertTbVipPay(tbVipPay);
    }

    /**
     * 修改会员支付信息
     * 
     * @param tbVipPay 会员支付信息
     * @return 结果
     */
    @Override
    public int updateTbVipPay(TbVipPay tbVipPay)
    {
        tbVipPay.setUpdateTime(DateUtils.getNowDate());
         tbVipPay.setUpdateBy(ShiroUtils.getLoginName());
        return tbVipPayMapper.updateTbVipPay(tbVipPay);
    }

    /**
     * 批量删除会员支付信息
     * 
     * @param ids 需要删除的会员支付信息主键
     * @return 结果
     */
    @Override
    public int deleteTbVipPayByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbVipPayMapper.deleteTbVipPayByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除会员支付信息信息
     * 
     * @param id 会员支付信息主键
     * @return 结果
     */
    @Override
    public int deleteTbVipPayById(Long id)
    {
        return tbVipPayMapper.deleteTbVipPayById(id);
    }

    @Override
    public void updateVipGradeDaily(){
        tbVipPayMapper.updateVipGradeDaily();
    }
}
