package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbOperLogs;
import org.apache.ibatis.annotations.Param;

/**
 * 操作记录Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbOperLogsMapper 
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
     * 删除操作记录
     * 
     * @param id 操作记录主键
     * @return 结果
     */
    public int deleteTbOperLogsById(Long id);

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbOperLogsByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
