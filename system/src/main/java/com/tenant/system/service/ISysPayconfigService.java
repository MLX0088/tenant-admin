package com.tenant.system.service;

import java.util.List;
import java.util.Map;

import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.domain.pay.CommonOrder;
import com.tenant.common.domain.pay.SysPayconfig;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付通道配置Service接口
 * 
 * @author 栾钰
 * @date 2023-02-20
 */
public interface ISysPayconfigService 
{
    /**
     * 查询支付通道配置
     * 
     * @param id 支付通道配置主键
     * @return 支付通道配置
     */
    public SysPayconfig selectSysPayconfigById(Long id);

    /**
     * 查询支付通道配置列表
     * 
     * @param sysPayconfig 支付通道配置
     * @return 支付通道配置集合
     */
    public List<SysPayconfig> selectSysPayconfigList(SysPayconfig sysPayconfig);

    /**
     * 新增支付通道配置
     * 
     * @param sysPayconfig 支付通道配置
     * @return 结果
     */
    public int insertSysPayconfig(SysPayconfig sysPayconfig);

    /**
     * 修改支付通道配置
     * 
     * @param sysPayconfig 支付通道配置
     * @return 结果
     */
    public int updateSysPayconfig(SysPayconfig sysPayconfig);

    /**
     * 批量删除支付通道配置
     * 
     * @param ids 需要删除的支付通道配置主键集合
     * @return 结果
     */
    public int deleteSysPayconfigByIds(String ids);

    /**
     * 删除支付通道配置信息
     * 
     * @param id 支付通道配置主键
     * @return 结果
     */
    public int deleteSysPayconfigById(Long id);

    /**
     * 应用类型
     * @param appType
     * @return
     */
    SysPayconfig getWxClient(String appType);
    /**
     * 应用类型
     * @param appType
     * @return
     */
    SysPayconfig   getAlipayClient(String appType);
    public AjaxResult dealAlipay(CommonOrder order);
    public AjaxResult dealWeChatPay(CommonOrder order);
    public String aliPayCertNotify(Map map);

    String wechatPayNotify(HttpServletRequest request);

    /**
     * 退款
     * @param order
     * @return
     */
    AjaxResult refund(CommonOrder order);
}
