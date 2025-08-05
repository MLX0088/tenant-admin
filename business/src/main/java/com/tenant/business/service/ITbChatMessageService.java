package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbChatMessage;

/**
 * 消息Service接口
 * 
 * @author luanyu
 * @date 2025-05-24
 */
public interface ITbChatMessageService 
{
    /**
     * 查询消息
     * 
     * @param id 消息主键
     * @return 消息
     */
    public TbChatMessage selectTbChatMessageById(Long id);

    /**
     * 查询消息列表
     * 
     * @param tbChatMessage 消息
     * @return 消息集合
     */
    public List<TbChatMessage> selectTbChatMessageList(TbChatMessage tbChatMessage);

    List<TbChatMessage> selectContactList(long tenantId);

    /**
     * 新增消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    public int insertTbChatMessage(TbChatMessage tbChatMessage);

    int selectUnreadCountByReceiverId(TbChatMessage tbChatMessage);

    /**
     * 修改消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    public int updateTbChatMessage(TbChatMessage tbChatMessage);

    int updateReadStatus(TbChatMessage tbChatMessage);

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的消息主键集合
     * @return 结果
     */
    public int deleteTbChatMessageByIds(String ids);

    /**
     * 删除消息信息
     * 
     * @param id 消息主键
     * @return 结果
     */
    public int deleteTbChatMessageById(Long id);

    int updateStatusForYesterday();

    public int deleteTbChatMessageForDays();
}
