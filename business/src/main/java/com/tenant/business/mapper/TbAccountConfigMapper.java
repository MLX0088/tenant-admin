package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbAccountConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 上/下分账号设置Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbAccountConfigMapper 
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
     * 删除上/下分账号设置
     * 
     * @param id 上/下分账号设置主键
     * @return 结果
     */
    public int deleteTbAccountConfigById(Long id);

    /**
     * 批量删除上/下分账号设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbAccountConfigByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
