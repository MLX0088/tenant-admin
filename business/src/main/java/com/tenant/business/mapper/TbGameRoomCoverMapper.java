package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbGameRoomCover;
import org.apache.ibatis.annotations.Param;

/**
 * 游戏房间封面Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbGameRoomCoverMapper 
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
     * 删除游戏房间封面
     * 
     * @param id 游戏房间封面主键
     * @return 结果
     */
    public int deleteTbGameRoomCoverById(Long id);

    /**
     * 批量删除游戏房间封面
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbGameRoomCoverByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
