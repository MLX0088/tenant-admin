package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbTenantConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 租客基础设置Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbTenantConfigMapper 
{
    /**
     * 查询租客基础设置
     * 
     * @param id 租客基础设置主键
     * @return 租客基础设置
     */
    public TbTenantConfig selectTbTenantConfigById(Long id);

    public TbTenantConfig selectTbTenantConfigByTenantId(Long id);

    /**
     * 查询租客基础设置列表
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 租客基础设置集合
     */
    public List<TbTenantConfig> selectTbTenantConfigList(TbTenantConfig tbTenantConfig);

    /**
     * 新增租客基础设置
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 结果
     */
    public int insertTbTenantConfig(TbTenantConfig tbTenantConfig);

    /**
     * 修改租客基础设置
     * 
     * @param tbTenantConfig 租客基础设置
     * @return 结果
     */
    public int updateTbTenantConfig(TbTenantConfig tbTenantConfig);

    /**
     * 删除租客基础设置
     * 
     * @param id 租客基础设置主键
     * @return 结果
     */
    public int deleteTbTenantConfigById(Long id);

    /**
     * 批量删除租客基础设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbTenantConfigByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);

    public TbTenantConfig checkInviteCode(String inviteCode);

    public void generateTenantConfigs(Long tenantId);
}
