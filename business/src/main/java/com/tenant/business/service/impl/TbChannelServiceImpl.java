package com.tenant.business.service.impl;

import java.util.List;

import com.tenant.business.domain.statistics.ChannelRecord;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbChannelMapper;
import com.tenant.business.domain.TbChannel;
import com.tenant.business.service.ITbChannelService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 渠道来源Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbChannelServiceImpl implements ITbChannelService 
{
    @Autowired
    private TbChannelMapper tbChannelMapper;

    /**
     * 查询渠道来源
     * 
     * @param id 渠道来源主键
     * @return 渠道来源
     */
    @Override
    public TbChannel selectTbChannelById(Long id)
    {
        return tbChannelMapper.selectTbChannelById(id);
    }

    /**
     * 查询渠道来源列表
     * 
     * @param tbChannel 渠道来源
     * @return 渠道来源
     */
    @Override
    public List<TbChannel> selectTbChannelList(TbChannel tbChannel)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbChannel.setCreateBy(sysUser.getLoginName());
        }
        return tbChannelMapper.selectTbChannelList(tbChannel);
    }

    @Override
    public List<ChannelRecord> selectChannelList(TbChannel tbChannel){
        return tbChannelMapper.selectChannelList(tbChannel);
    }

    /**
     * 新增渠道来源
     * 
     * @param tbChannel 渠道来源
     * @return 结果
     */
    @Override
    public int insertTbChannel(TbChannel tbChannel)
    {
        tbChannel.setCreateTime(DateUtils.getNowDate());
        tbChannel.setCreateBy(ShiroUtils.getLoginName());
        return tbChannelMapper.insertTbChannel(tbChannel);
    }

    /**
     * 修改渠道来源
     * 
     * @param tbChannel 渠道来源
     * @return 结果
     */
    @Override
    public int updateTbChannel(TbChannel tbChannel)
    {
        tbChannel.setUpdateTime(DateUtils.getNowDate());
         tbChannel.setUpdateBy(ShiroUtils.getLoginName());
        return tbChannelMapper.updateTbChannel(tbChannel);
    }

    /**
     * 批量删除渠道来源
     * 
     * @param ids 需要删除的渠道来源主键
     * @return 结果
     */
    @Override
    public int deleteTbChannelByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbChannelMapper.deleteTbChannelByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除渠道来源信息
     * 
     * @param id 渠道来源主键
     * @return 结果
     */
    @Override
    public int deleteTbChannelById(Long id)
    {
        return tbChannelMapper.deleteTbChannelById(id);
    }
}
