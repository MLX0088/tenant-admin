package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbTenantNotice;
import org.apache.ibatis.annotations.Param;

/**
 * 租户公告管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-08
 */
public interface TbTenantNoticeMapper 
{
    /**
     * 查询租户公告管理
     * 
     * @param id 租户公告管理主键
     * @return 租户公告管理
     */
    public TbTenantNotice selectTbTenantNoticeById(Long id);

    /**
     * 查询租户公告管理列表
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 租户公告管理集合
     */
    public List<TbTenantNotice> selectTbTenantNoticeList(TbTenantNotice tbTenantNotice);

    /**
     * 新增租户公告管理
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 结果
     */
    public int insertTbTenantNotice(TbTenantNotice tbTenantNotice);

    /**
     * 修改租户公告管理
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 结果
     */
    public int updateTbTenantNotice(TbTenantNotice tbTenantNotice);

    /**
     * 删除租户公告管理
     * 
     * @param id 租户公告管理主键
     * @return 结果
     */
    public int deleteTbTenantNoticeById(Long id);

    /**
     * 批量删除租户公告管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbTenantNoticeByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
