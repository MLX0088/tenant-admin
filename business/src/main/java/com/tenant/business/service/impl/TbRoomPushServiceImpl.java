package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbRoomPushMapper;
import com.tenant.business.domain.TbRoomPush;
import com.tenant.business.service.ITbRoomPushService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 房间推送信息Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-06
 */
@Service
public class TbRoomPushServiceImpl implements ITbRoomPushService 
{
    @Autowired
    private TbRoomPushMapper tbRoomPushMapper;

    /**
     * 查询房间推送信息
     * 
     * @param id 房间推送信息主键
     * @return 房间推送信息
     */
    @Override
    public TbRoomPush selectTbRoomPushById(Long id)
    {
        return tbRoomPushMapper.selectTbRoomPushById(id);
    }

    /**
     * 查询房间推送信息列表
     * 
     * @param tbRoomPush 房间推送信息
     * @return 房间推送信息
     */
    @Override
    public List<TbRoomPush> selectTbRoomPushList(TbRoomPush tbRoomPush)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbRoomPush.setCreateBy(sysUser.getLoginName());
        }
        return tbRoomPushMapper.selectTbRoomPushList(tbRoomPush);
    }

    /**
     * 新增房间推送信息
     * 
     * @param tbRoomPush 房间推送信息
     * @return 结果
     */
    @Override
    public int insertTbRoomPush(TbRoomPush tbRoomPush)
    {
        tbRoomPush.setCreateTime(DateUtils.getNowDate());
        tbRoomPush.setCreateBy(ShiroUtils.getLoginName());
        return tbRoomPushMapper.insertTbRoomPush(tbRoomPush);
    }

    /**
     * 修改房间推送信息
     * 
     * @param tbRoomPush 房间推送信息
     * @return 结果
     */
    @Override
    public int updateTbRoomPush(TbRoomPush tbRoomPush)
    {
        tbRoomPush.setUpdateTime(DateUtils.getNowDate());
         tbRoomPush.setUpdateBy(ShiroUtils.getLoginName());
        return tbRoomPushMapper.updateTbRoomPush(tbRoomPush);
    }

    /**
     * 批量删除房间推送信息
     * 
     * @param ids 需要删除的房间推送信息主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomPushByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbRoomPushMapper.deleteTbRoomPushByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除房间推送信息信息
     * 
     * @param id 房间推送信息主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomPushById(Long id)
    {
        return tbRoomPushMapper.deleteTbRoomPushById(id);
    }
    @Override
    public int deleteTbRoomPushByTenantIdAndRoomName(Long tenantId,String roomName)
    {
        return tbRoomPushMapper.deleteTbRoomPushByTenantIdAndRoomName(tenantId, roomName);
    }
}
