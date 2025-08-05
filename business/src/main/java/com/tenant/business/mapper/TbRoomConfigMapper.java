package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbRoomConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 房间Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbRoomConfigMapper 
{
    /**
     * 查询房间
     * 
     * @param id 房间主键
     * @return 房间
     */
    public TbRoomConfig selectTbRoomConfigById(Long id);

    /**
     * 查询房间列表
     * 
     * @param tbRoomConfig 房间
     * @return 房间集合
     */
    public List<TbRoomConfig> selectTbRoomConfigList(TbRoomConfig tbRoomConfig);

    /**
     * 新增房间
     * 
     * @param tbRoomConfig 房间
     * @return 结果
     */
    public int insertTbRoomConfig(TbRoomConfig tbRoomConfig);

    /**
     * 修改房间
     * 
     * @param tbRoomConfig 房间
     * @return 结果
     */
    public int updateTbRoomConfig(TbRoomConfig tbRoomConfig);

    /**
     * 删除房间
     * 
     * @param id 房间主键
     * @return 结果
     */
    public int deleteTbRoomConfigById(Long id);

    /**
     * 批量删除房间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbRoomConfigByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
