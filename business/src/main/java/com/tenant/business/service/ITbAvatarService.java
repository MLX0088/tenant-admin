package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbAvatar;

/**
 * 头像管理Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbAvatarService 
{
    /**
     * 查询头像管理
     * 
     * @param id 头像管理主键
     * @return 头像管理
     */
    public TbAvatar selectTbAvatarById(Long id);

    /**
     * 查询头像管理列表
     * 
     * @param tbAvatar 头像管理
     * @return 头像管理集合
     */
    public List<TbAvatar> selectTbAvatarList(TbAvatar tbAvatar);

    /**
     * 新增头像管理
     * 
     * @param tbAvatar 头像管理
     * @return 结果
     */
    public int insertTbAvatar(TbAvatar tbAvatar);

    /**
     * 修改头像管理
     * 
     * @param tbAvatar 头像管理
     * @return 结果
     */
    public int updateTbAvatar(TbAvatar tbAvatar);

    /**
     * 批量删除头像管理
     * 
     * @param ids 需要删除的头像管理主键集合
     * @return 结果
     */
    public int deleteTbAvatarByIds(String ids);

    /**
     * 删除头像管理信息
     * 
     * @param id 头像管理主键
     * @return 结果
     */
    public int deleteTbAvatarById(Long id);

    public long getRandomIdForTenant(Long tenantId);
}
