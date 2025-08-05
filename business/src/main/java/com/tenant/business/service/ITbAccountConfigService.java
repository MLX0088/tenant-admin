package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbAccountConfig;

/**
 * 上/下分账号设置Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbAccountConfigService 
{
    /**
     * 查询上/下分账号设置
     * 
     * @param id 上/下分账号设置主键
     * @return 上/下分账号设置
     */
    public TbAccountConfig selectTbAccountConfigById(Long id);

    /**
     * 查询上/下分账号设置列表
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 上/下分账号设置集合
     */
    public List<TbAccountConfig> selectTbAccountConfigList(TbAccountConfig tbAccountConfig);

    /**
     * 新增上/下分账号设置
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 结果
     */
    public int insertTbAccountConfig(TbAccountConfig tbAccountConfig);

    /**
     * 修改上/下分账号设置
     * 
     * @param tbAccountConfig 上/下分账号设置
     * @return 结果
     */
    public int updateTbAccountConfig(TbAccountConfig tbAccountConfig);

    /**
     * 批量删除上/下分账号设置
     * 
     * @param ids 需要删除的上/下分账号设置主键集合
     * @return 结果
     */
    public int deleteTbAccountConfigByIds(String ids);

    /**
     * 删除上/下分账号设置信息
     * 
     * @param id 上/下分账号设置主键
     * @return 结果
     */
    public int deleteTbAccountConfigById(Long id);
}
