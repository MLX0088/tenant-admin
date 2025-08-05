package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbRoomPush;

/**
 * 房间推送信息Service接口
 * 
 * @author luanyu
 * @date 2025-05-06
 */
public interface ITbRoomPushService 
{
    /**
     * 查询房间推送信息
     * 
     * @param id 房间推送信息主键
     * @return 房间推送信息
     */
    public TbRoomPush selectTbRoomPushById(Long id);

    /**
     * 查询房间推送信息列表
     * 
     * @param tbRoomPush 房间推送信息
     * @return 房间推送信息集合
     */
    public List<TbRoomPush> selectTbRoomPushList(TbRoomPush tbRoomPush);

    /**
     * 新增房间推送信息
     * 
     * @param tbRoomPush 房间推送信息
     * @return 结果
     */
    public int insertTbRoomPush(TbRoomPush tbRoomPush);

    /**
     * 修改房间推送信息
     * 
     * @param tbRoomPush 房间推送信息
     * @return 结果
     */
    public int updateTbRoomPush(TbRoomPush tbRoomPush);

    /**
     * 批量删除房间推送信息
     * 
     * @param ids 需要删除的房间推送信息主键集合
     * @return 结果
     */
    public int deleteTbRoomPushByIds(String ids);

    /**
     * 删除房间推送信息信息
     * 
     * @param id 房间推送信息主键
     * @return 结果
     */
    public int deleteTbRoomPushById(Long id);

    int deleteTbRoomPushByTenantIdAndRoomName(Long tenantId,String roomName);
}
