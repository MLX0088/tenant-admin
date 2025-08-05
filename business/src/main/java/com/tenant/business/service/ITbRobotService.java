package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbRobot;

/**
 * 机器人管理Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbRobotService 
{
    /**
     * 查询机器人管理
     * 
     * @param id 机器人管理主键
     * @return 机器人管理
     */
    public TbRobot selectTbRobotById(Long id);

    /**
     * 查询机器人管理列表
     * 
     * @param tbRobot 机器人管理
     * @return 机器人管理集合
     */
    public List<TbRobot> selectTbRobotList(TbRobot tbRobot);

    /**
     * 新增机器人管理
     * 
     * @param tbRobot 机器人管理
     * @return 结果
     */
    public int insertTbRobot(TbRobot tbRobot);

    /**
     * 修改机器人管理
     * 
     * @param tbRobot 机器人管理
     * @return 结果
     */
    public int updateTbRobot(TbRobot tbRobot);

    /**
     * 批量删除机器人管理
     * 
     * @param ids 需要删除的机器人管理主键集合
     * @return 结果
     */
    public int deleteTbRobotByIds(String ids);

    /**
     * 删除机器人管理信息
     * 
     * @param id 机器人管理主键
     * @return 结果
     */
    public int deleteTbRobotById(Long id);

    void createRebotForTenantId(Long id, int count, String[] rooms);

    List<TbRobot> selectRobotForScoreFilter(int min);
}
