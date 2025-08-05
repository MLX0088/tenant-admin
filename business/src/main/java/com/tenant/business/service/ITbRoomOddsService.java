package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbRoomOdds;

/**
 * 房间赔率Service接口
 * 
 * @author luanyu
 * @date 2025-05-06
 */
public interface ITbRoomOddsService 
{
    /**
     * 查询房间赔率
     * 
     * @param id 房间赔率主键
     * @return 房间赔率
     */
    public TbRoomOdds selectTbRoomOddsById(Long id);

    /**
     * 查询房间赔率列表
     * 
     * @param tbRoomOdds 房间赔率
     * @return 房间赔率集合
     */
    public List<TbRoomOdds> selectTbRoomOddsList(TbRoomOdds tbRoomOdds);

    /**
     * 新增房间赔率
     * 
     * @param tbRoomOdds 房间赔率
     * @return 结果
     */
    public int insertTbRoomOdds(TbRoomOdds tbRoomOdds);

    /**
     * 修改房间赔率
     * 
     * @param tbRoomOdds 房间赔率
     * @return 结果
     */
    public int updateTbRoomOdds(TbRoomOdds tbRoomOdds);

    /**
     * 批量删除房间赔率
     * 
     * @param ids 需要删除的房间赔率主键集合
     * @return 结果
     */
    public int deleteTbRoomOddsByIds(String ids);

    /**
     * 删除房间赔率信息
     * 
     * @param id 房间赔率主键
     * @return 结果
     */
    public int deleteTbRoomOddsById(Long id);
}
