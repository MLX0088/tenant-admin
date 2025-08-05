package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbAvatarMapper;
import com.tenant.business.domain.TbAvatar;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 头像管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbAvatarServiceImpl implements ITbAvatarService 
{
    @Autowired
    private TbAvatarMapper tbAvatarMapper;

    /**
     * 查询头像管理
     * 
     * @param id 头像管理主键
     * @return 头像管理
     */
    @Override
    public TbAvatar selectTbAvatarById(Long id)
    {
        return tbAvatarMapper.selectTbAvatarById(id);
    }

    /**
     * 查询头像管理列表
     * 
     * @param tbAvatar 头像管理
     * @return 头像管理
     */
    @Override
    public List<TbAvatar> selectTbAvatarList(TbAvatar tbAvatar)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbAvatar.setCreateBy(sysUser.getLoginName());
        }
        return tbAvatarMapper.selectTbAvatarList(tbAvatar);
    }

    /**
     * 新增头像管理
     * 
     * @param tbAvatar 头像管理
     * @return 结果
     */
    @Override
    public int insertTbAvatar(TbAvatar tbAvatar)
    {
        tbAvatar.setCreateTime(DateUtils.getNowDate());
        tbAvatar.setCreateBy(ShiroUtils.getLoginName());
        return tbAvatarMapper.insertTbAvatar(tbAvatar);
    }

    /**
     * 修改头像管理
     * 
     * @param tbAvatar 头像管理
     * @return 结果
     */
    @Override
    public int updateTbAvatar(TbAvatar tbAvatar)
    {
        tbAvatar.setUpdateTime(DateUtils.getNowDate());
         tbAvatar.setUpdateBy(ShiroUtils.getLoginName());
        return tbAvatarMapper.updateTbAvatar(tbAvatar);
    }

    /**
     * 批量删除头像管理
     * 
     * @param ids 需要删除的头像管理主键
     * @return 结果
     */
    @Override
    public int deleteTbAvatarByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbAvatarMapper.deleteTbAvatarByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除头像管理信息
     * 
     * @param id 头像管理主键
     * @return 结果
     */
    @Override
    public int deleteTbAvatarById(Long id)
    {
        return tbAvatarMapper.deleteTbAvatarById(id);
    }

    @Override
    public long getRandomIdForTenant(Long tenantId){
        return tbAvatarMapper.getRandomIdForTenant(tenantId);
    }
}
