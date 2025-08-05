package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbGameRoomCover;

/**
 * 游戏房间封面Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbGameRoomCoverService 
{
    /**
     * 查询游戏房间封面
     * 
     * @param id 游戏房间封面主键
     * @return 游戏房间封面
     */
    public TbGameRoomCover selectTbGameRoomCoverById(Long id);

    /**
     * 查询游戏房间封面列表
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 游戏房间封面集合
     */
    public List<TbGameRoomCover> selectTbGameRoomCoverList(TbGameRoomCover tbGameRoomCover);

    /**
     * 新增游戏房间封面
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 结果
     */
    public int insertTbGameRoomCover(TbGameRoomCover tbGameRoomCover);

    /**
     * 修改游戏房间封面
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 结果
     */
    public int updateTbGameRoomCover(TbGameRoomCover tbGameRoomCover);

    /**
     * 批量删除游戏房间封面
     * 
     * @param ids 需要删除的游戏房间封面主键集合
     * @return 结果
     */
    public int deleteTbGameRoomCoverByIds(String ids);

    /**
     * 删除游戏房间封面信息
     * 
     * @param id 游戏房间封面主键
     * @return 结果
     */
    public int deleteTbGameRoomCoverById(Long id);
}
