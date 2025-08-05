package com.tenant.system.mapper;

import java.util.List;
import com.tenant.system.domain.SysAd;
import org.apache.ibatis.annotations.Param;

/**
 * 广告管理Mapper接口
 * 
 * @author luanyu
 * @date 2023-03-14
 */
public interface SysAdMapper 
{
    /**
     * 查询广告管理
     * 
     * @param id 广告管理主键
     * @return 广告管理
     */
    public SysAd selectSysAdById(Long id);

    /**
     * 查询广告管理列表
     * 
     * @param sysAd 广告管理
     * @return 广告管理集合
     */
    public List<SysAd> selectSysAdList(SysAd sysAd);

    /**
     * 新增广告管理
     * 
     * @param sysAd 广告管理
     * @return 结果
     */
    public int insertSysAd(SysAd sysAd);

    /**
     * 修改广告管理
     * 
     * @param sysAd 广告管理
     * @return 结果
     */
    public int updateSysAd(SysAd sysAd);

    /**
     * 删除广告管理
     * 
     * @param id 广告管理主键
     * @return 结果
     */
    public int deleteSysAdById(Long id);

    /**
     * 批量删除广告管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAdByIds(@Param("ids")String[] ids, @Param("createBy") String createBy);
}
