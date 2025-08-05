package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbAvatar;
import org.apache.ibatis.annotations.Param;

/**
 * 头像管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbAvatarMapper 
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
     * 删除头像管理
     * 
     * @param id 头像管理主键
     * @return 结果
     */
    public int deleteTbAvatarById(Long id);

    /**
     * 批量删除头像管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbAvatarByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);

    public long getRandomIdForTenant(Long tenantId);
}
