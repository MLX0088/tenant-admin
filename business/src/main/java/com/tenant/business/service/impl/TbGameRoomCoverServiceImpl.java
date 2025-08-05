package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbGameRoomCoverMapper;
import com.tenant.business.domain.TbGameRoomCover;
import com.tenant.business.service.ITbGameRoomCoverService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 游戏房间封面Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbGameRoomCoverServiceImpl implements ITbGameRoomCoverService 
{
    @Autowired
    private TbGameRoomCoverMapper tbGameRoomCoverMapper;

    /**
     * 查询游戏房间封面
     * 
     * @param id 游戏房间封面主键
     * @return 游戏房间封面
     */
    @Override
    public TbGameRoomCover selectTbGameRoomCoverById(Long id)
    {
        return tbGameRoomCoverMapper.selectTbGameRoomCoverById(id);
    }

    /**
     * 查询游戏房间封面列表
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 游戏房间封面
     */
    @Override
    public List<TbGameRoomCover> selectTbGameRoomCoverList(TbGameRoomCover tbGameRoomCover)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbGameRoomCover.setCreateBy(sysUser.getLoginName());
        }
        return tbGameRoomCoverMapper.selectTbGameRoomCoverList(tbGameRoomCover);
    }

    /**
     * 新增游戏房间封面
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 结果
     */
    @Override
    public int insertTbGameRoomCover(TbGameRoomCover tbGameRoomCover)
    {
        tbGameRoomCover.setCreateTime(DateUtils.getNowDate());
        tbGameRoomCover.setCreateBy(ShiroUtils.getLoginName());
        return tbGameRoomCoverMapper.insertTbGameRoomCover(tbGameRoomCover);
    }

    /**
     * 修改游戏房间封面
     * 
     * @param tbGameRoomCover 游戏房间封面
     * @return 结果
     */
    @Override
    public int updateTbGameRoomCover(TbGameRoomCover tbGameRoomCover)
    {
        tbGameRoomCover.setUpdateTime(DateUtils.getNowDate());
         tbGameRoomCover.setUpdateBy(ShiroUtils.getLoginName());
        return tbGameRoomCoverMapper.updateTbGameRoomCover(tbGameRoomCover);
    }

    /**
     * 批量删除游戏房间封面
     * 
     * @param ids 需要删除的游戏房间封面主键
     * @return 结果
     */
    @Override
    public int deleteTbGameRoomCoverByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbGameRoomCoverMapper.deleteTbGameRoomCoverByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除游戏房间封面信息
     * 
     * @param id 游戏房间封面主键
     * @return 结果
     */
    @Override
    public int deleteTbGameRoomCoverById(Long id)
    {
        return tbGameRoomCoverMapper.deleteTbGameRoomCoverById(id);
    }
}
