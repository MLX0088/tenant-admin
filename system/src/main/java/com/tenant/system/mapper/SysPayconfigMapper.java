package com.tenant.system.mapper;

import java.util.List;
import com.tenant.common.domain.pay.SysPayconfig;

/**
 * 支付通道配置Mapper接口
 * 
 * @author 栾钰
 * @date 2023-02-20
 */
public interface SysPayconfigMapper 
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
     * 删除支付通道配置
     * 
     * @param id 支付通道配置主键
     * @return 结果
     */
    public int deleteSysPayconfigById(Long id);

    /**
     * 批量删除支付通道配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPayconfigByIds(String[] ids);
}
