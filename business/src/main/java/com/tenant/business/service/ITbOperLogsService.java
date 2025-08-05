package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbOperLogs;

/**
 * 操作记录Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbOperLogsService 
{
    /**
     * 查询操作记录
     * 
     * @param id 操作记录主键
     * @return 操作记录
     */
    public TbOperLogs selectTbOperLogsById(Long id);

    /**
     * 查询操作记录列表
     * 
     * @param tbOperLogs 操作记录
     * @return 操作记录集合
     */
    public List<TbOperLogs> selectTbOperLogsList(TbOperLogs tbOperLogs);

    /**
     * 新增操作记录
     * 
     * @param tbOperLogs 操作记录
     * @return 结果
     */
    public int insertTbOperLogs(TbOperLogs tbOperLogs);

    /**
     * 修改操作记录
     * 
     * @param tbOperLogs 操作记录
     * @return 结果
     */
    public int updateTbOperLogs(TbOperLogs tbOperLogs);

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的操作记录主键集合
     * @return 结果
     */
    public int deleteTbOperLogsByIds(String ids);

    /**
     * 删除操作记录信息
     * 
     * @param id 操作记录主键
     * @return 结果
     */
    public int deleteTbOperLogsById(Long id);
}
