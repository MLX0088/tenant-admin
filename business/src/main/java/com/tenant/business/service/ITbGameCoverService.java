package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbGameCover;

/**
 * 游戏类型封面Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbGameCoverService 
{
    /**
     * 查询游戏类型封面
     * 
     * @param id 游戏类型封面主键
     * @return 游戏类型封面
     */
    public TbGameCover selectTbGameCoverById(Long id);

    /**
     * 查询游戏类型封面列表
     * 
     * @param tbGameCover 游戏类型封面
     * @return 游戏类型封面集合
     */
    public List<TbGameCover> selectTbGameCoverList(TbGameCover tbGameCover);

    /**
     * 新增游戏类型封面
     * 
     * @param tbGameCover 游戏类型封面
     * @return 结果
     */
    public int insertTbGameCover(TbGameCover tbGameCover);

    /**
     * 修改游戏类型封面
     * 
     * @param tbGameCover 游戏类型封面
     * @return 结果
     */
    public int updateTbGameCover(TbGameCover tbGameCover);

    /**
     * 批量删除游戏类型封面
     * 
     * @param ids 需要删除的游戏类型封面主键集合
     * @return 结果
     */
    public int deleteTbGameCoverByIds(String ids);

    /**
     * 删除游戏类型封面信息
     * 
     * @param id 游戏类型封面主键
     * @return 结果
     */
    public int deleteTbGameCoverById(Long id);
}
