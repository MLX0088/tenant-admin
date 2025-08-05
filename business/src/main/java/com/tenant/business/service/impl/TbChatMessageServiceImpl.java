package com.tenant.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbChatMessageMapper;
import com.tenant.business.domain.TbChatMessage;
import com.tenant.business.service.ITbChatMessageService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 消息Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-24
 */
@Service
public class TbChatMessageServiceImpl implements ITbChatMessageService 
{
    @Autowired
    private TbChatMessageMapper tbChatMessageMapper;

    /**
     * 查询消息
     * 
     * @param id 消息主键
     * @return 消息
     */
    @Override
    public TbChatMessage selectTbChatMessageById(Long id)
    {
        return tbChatMessageMapper.selectTbChatMessageById(id);
    }

    /**
     * 查询消息列表
     * 
     * @param tbChatMessage 消息
     * @return 消息
     */
    @Override
    public List<TbChatMessage> selectTbChatMessageList(TbChatMessage tbChatMessage)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbChatMessage.setCreateBy(sysUser.getLoginName());
        }
        return tbChatMessageMapper.selectTbChatMessageList(tbChatMessage);
    }

    @Override
    public List<TbChatMessage> selectContactList(long tenantId){
        return tbChatMessageMapper.selectContactList(tenantId);
    }

    /**
     * 新增消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    @Override
    public int insertTbChatMessage(TbChatMessage tbChatMessage)
    {
        return tbChatMessageMapper.insertTbChatMessage(tbChatMessage);
    }
    @Override
    public int selectUnreadCountByReceiverId(TbChatMessage tbChatMessage){
        return tbChatMessageMapper.selectUnreadCountByReceiverId(tbChatMessage);
    }
    /**
     * 修改消息
     * 
     * @param tbChatMessage 消息
     * @return 结果
     */
    @Override
    public int updateTbChatMessage(TbChatMessage tbChatMessage)
    {
        return tbChatMessageMapper.updateTbChatMessage(tbChatMessage);
    }
    @Override
    public int updateReadStatus(TbChatMessage tbChatMessage){
        return tbChatMessageMapper.updateReadStatus(tbChatMessage);
    }

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteTbChatMessageByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbChatMessageMapper.deleteTbChatMessageByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除消息信息
     * 
     * @param id 消息主键
     * @return 结果
     */
    @Override
    public int deleteTbChatMessageById(Long id)
    {
        return tbChatMessageMapper.deleteTbChatMessageById(id);
    }

    @Override
    public int updateStatusForYesterday(){
        return tbChatMessageMapper.updateStatusForYesterday();
    }


    @Override
    public int deleteTbChatMessageForDays(){
        return tbChatMessageMapper.deleteTbChatMessageForDays();
    }
}
