package com.tenant.system.service;

import java.util.List;
import com.tenant.system.domain.SysAd;

/**
 * 广告管理Service接口
 * 
 * @author luanyu
 * @date 2023-03-14
 */
public interface ISysAdService 
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
     * 批量删除广告管理
     * 
     * @param ids 需要删除的广告管理主键集合
     * @return 结果
     */
    public int deleteSysAdByIds(String ids);

    /**
     * 删除广告管理信息
     * 
     * @param id 广告管理主键
     * @return 结果
     */
    public int deleteSysAdById(Long id);
}
