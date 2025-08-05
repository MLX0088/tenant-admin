package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbQuickReplyMapper;
import com.tenant.business.domain.TbQuickReply;
import com.tenant.business.service.ITbQuickReplyService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 快捷语管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbQuickReplyServiceImpl implements ITbQuickReplyService 
{
    @Autowired
    private TbQuickReplyMapper tbQuickReplyMapper;

    /**
     * 查询快捷语管理
     * 
     * @param id 快捷语管理主键
     * @return 快捷语管理
     */
    @Override
    public TbQuickReply selectTbQuickReplyById(Long id)
    {
        return tbQuickReplyMapper.selectTbQuickReplyById(id);
    }

    /**
     * 查询快捷语管理列表
     * 
     * @param tbQuickReply 快捷语管理
     * @return 快捷语管理
     */
    @Override
    public List<TbQuickReply> selectTbQuickReplyList(TbQuickReply tbQuickReply)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbQuickReply.setCreateBy(sysUser.getLoginName());
        }
        return tbQuickReplyMapper.selectTbQuickReplyList(tbQuickReply);
    }

    /**
     * 新增快捷语管理
     * 
     * @param tbQuickReply 快捷语管理
     * @return 结果
     */
    @Override
    public int insertTbQuickReply(TbQuickReply tbQuickReply)
    {
        tbQuickReply.setCreateTime(DateUtils.getNowDate());
        tbQuickReply.setCreateBy(ShiroUtils.getLoginName());
        return tbQuickReplyMapper.insertTbQuickReply(tbQuickReply);
    }

    /**
     * 修改快捷语管理
     * 
     * @param tbQuickReply 快捷语管理
     * @return 结果
     */
    @Override
    public int updateTbQuickReply(TbQuickReply tbQuickReply)
    {
        tbQuickReply.setUpdateTime(DateUtils.getNowDate());
         tbQuickReply.setUpdateBy(ShiroUtils.getLoginName());
        return tbQuickReplyMapper.updateTbQuickReply(tbQuickReply);
    }

    /**
     * 批量删除快捷语管理
     * 
     * @param ids 需要删除的快捷语管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuickReplyByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbQuickReplyMapper.deleteTbQuickReplyByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除快捷语管理信息
     * 
     * @param id 快捷语管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuickReplyById(Long id)
    {
        return tbQuickReplyMapper.deleteTbQuickReplyById(id);
    }
}
