package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbGameCover;
import org.apache.ibatis.annotations.Param;

/**
 * 游戏类型封面Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbGameCoverMapper 
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
     * 删除游戏类型封面
     * 
     * @param id 游戏类型封面主键
     * @return 结果
     */
    public int deleteTbGameCoverById(Long id);

    /**
     * 批量删除游戏类型封面
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbGameCoverByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
