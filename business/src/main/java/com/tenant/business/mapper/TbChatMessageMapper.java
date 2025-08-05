package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbChatMessage;
import org.apache.ibatis.annotations.Param;

/**
 * 消息Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-24
 */
public interface TbChatMessageMapper 
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

    public List<TbChatMessage> selectContactList(long tenantId);

    /**
     * 新增消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    public int insertTbChatMessage(TbChatMessage tbChatMessage);

    public int selectUnreadCountByReceiverId(TbChatMessage tbChatMessage);
    /**
     * 修改消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    public int updateTbChatMessage(TbChatMessage tbChatMessage);

    public int updateReadStatus(TbChatMessage tbChatMessage);

    /**
     * 删除消息
     * 
     * @param id 消息主键
     * @return 结果
     */
    public int deleteTbChatMessageById(Long id);
    public int deleteTbChatMessageForDays();

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbChatMessageByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);

    public int updateStatusForYesterday();
}
