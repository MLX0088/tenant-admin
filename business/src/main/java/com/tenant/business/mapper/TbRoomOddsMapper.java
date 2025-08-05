package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbRoomOdds;
import org.apache.ibatis.annotations.Param;

/**
 * 房间赔率Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-06
 */
public interface TbRoomOddsMapper 
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
     * 删除房间赔率
     * 
     * @param id 房间赔率主键
     * @return 结果
     */
    public int deleteTbRoomOddsById(Long id);

    /**
     * 批量删除房间赔率
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbRoomOddsByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
