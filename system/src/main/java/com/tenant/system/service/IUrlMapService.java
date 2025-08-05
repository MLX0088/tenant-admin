package com.tenant.system.service;

import java.util.List;
import com.tenant.system.domain.UrlMap;

/**
 * 短链Service接口
 * 
 * @author 栾钰
 * @date 2023-03-07
 */
public interface IUrlMapService 
{
    /**
     * 查询短链
     * 
     * @param id 短链主键
     * @return 短链
     */
    public UrlMap selectUrlMapById(Long id);

    /**
     * 查询短链列表
     * 
     * @param urlMap 短链
     * @return 短链集合
     */
    public List<UrlMap> selectUrlMapList(UrlMap urlMap);

    /**
     * 新增短链
     * 
     * @param urlMap 短链
     * @return 结果
     */
    public int insertUrlMap(UrlMap urlMap);

    /**
     * 修改短链
     * 
     * @param urlMap 短链
     * @return 结果
     */
    public int updateUrlMap(UrlMap urlMap);

    /**
     * 批量删除短链
     * 
     * @param ids 需要删除的短链主键集合
     * @return 结果
     */
    public int deleteUrlMapByIds(String ids);

    /**
     * 删除短链信息
     * 
     * @param id 短链主键
     * @return 结果
     */
    public int deleteUrlMapById(Long id);
}
