package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbVipPay;

/**
 * 会员支付信息Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbVipPayService 
{
    /**
     * 查询会员支付信息
     * 
     * @param id 会员支付信息主键
     * @return 会员支付信息
     */
    public TbVipPay selectTbVipPayById(Long id);

    /**
     * 查询会员支付信息列表
     * 
     * @param tbVipPay 会员支付信息
     * @return 会员支付信息集合
     */
    public List<TbVipPay> selectTbVipPayList(TbVipPay tbVipPay);

    /**
     * 新增会员支付信息
     * 
     * @param tbVipPay 会员支付信息
     * @return 结果
     */
    public int insertTbVipPay(TbVipPay tbVipPay);

    /**
     * 修改会员支付信息
     * 
     * @param tbVipPay 会员支付信息
     * @return 结果
     */
    public int updateTbVipPay(TbVipPay tbVipPay);

    /**
     * 批量删除会员支付信息
     * 
     * @param ids 需要删除的会员支付信息主键集合
     * @return 结果
     */
    public int deleteTbVipPayByIds(String ids);

    /**
     * 删除会员支付信息信息
     * 
     * @param id 会员支付信息主键
     * @return 结果
     */
    public int deleteTbVipPayById(Long id);

    public void updateVipGradeDaily();
}
