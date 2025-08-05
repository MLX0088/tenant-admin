package com.tenant.system.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.system.mapper.UrlMapMapper;
import com.tenant.system.domain.UrlMap;
import com.tenant.system.service.IUrlMapService;
import com.tenant.common.core.text.Convert;

/**
 * 短链Service业务层处理
 * 
 * @author 栾钰
 * @date 2023-03-07
 */
@Service
public class UrlMapServiceImpl implements IUrlMapService 
{
    @Autowired
    private UrlMapMapper urlMapMapper;

    /**
     * 查询短链
     * 
     * @param id 短链主键
     * @return 短链
     */
    @Override
    public UrlMap selectUrlMapById(Long id)
    {
        return urlMapMapper.selectUrlMapById(id);
    }

    /**
     * 查询短链列表
     * 
     * @param urlMap 短链
     * @return 短链
     */
    @Override
    public List<UrlMap> selectUrlMapList(UrlMap urlMap)
    {
        return urlMapMapper.selectUrlMapList(urlMap);
    }

    /**
     * 新增短链
     * 
     * @param urlMap 短链
     * @return 结果
     */
    @Override
    public int insertUrlMap(UrlMap urlMap)
    {
        urlMap.setCreateTime(DateUtils.getNowDate());
        return urlMapMapper.insertUrlMap(urlMap);
    }

    /**
     * 修改短链
     * 
     * @param urlMap 短链
     * @return 结果
     */
    @Override
    public int updateUrlMap(UrlMap urlMap)
    {
        return urlMapMapper.updateUrlMap(urlMap);
    }

    /**
     * 批量删除短链
     * 
     * @param ids 需要删除的短链主键
     * @return 结果
     */
    @Override
    public int deleteUrlMapByIds(String ids)
    {
        return urlMapMapper.deleteUrlMapByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短链信息
     * 
     * @param id 短链主键
     * @return 结果
     */
    @Override
    public int deleteUrlMapById(Long id)
    {
        return urlMapMapper.deleteUrlMapById(id);
    }
}
