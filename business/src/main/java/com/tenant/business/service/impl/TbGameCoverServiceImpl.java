package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbGameCoverMapper;
import com.tenant.business.domain.TbGameCover;
import com.tenant.business.service.ITbGameCoverService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 游戏类型封面Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbGameCoverServiceImpl implements ITbGameCoverService 
{
    @Autowired
    private TbGameCoverMapper tbGameCoverMapper;

    /**
     * 查询游戏类型封面
     * 
     * @param id 游戏类型封面主键
     * @return 游戏类型封面
     */
    @Override
    public TbGameCover selectTbGameCoverById(Long id)
    {
        return tbGameCoverMapper.selectTbGameCoverById(id);
    }

    /**
     * 查询游戏类型封面列表
     * 
     * @param tbGameCover 游戏类型封面
     * @return 游戏类型封面
     */
    @Override
    public List<TbGameCover> selectTbGameCoverList(TbGameCover tbGameCover)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbGameCover.setCreateBy(sysUser.getLoginName());
        }
        return tbGameCoverMapper.selectTbGameCoverList(tbGameCover);
    }

    /**
     * 新增游戏类型封面
     * 
     * @param tbGameCover 游戏类型封面
     * @return 结果
     */
    @Override
    public int insertTbGameCover(TbGameCover tbGameCover)
    {
        tbGameCover.setCreateTime(DateUtils.getNowDate());
        tbGameCover.setCreateBy(ShiroUtils.getLoginName());
        return tbGameCoverMapper.insertTbGameCover(tbGameCover);
    }

    /**
     * 修改游戏类型封面
     * 
     * @param tbGameCover 游戏类型封面
     * @return 结果
     */
    @Override
    public int updateTbGameCover(TbGameCover tbGameCover)
    {
        tbGameCover.setUpdateTime(DateUtils.getNowDate());
         tbGameCover.setUpdateBy(ShiroUtils.getLoginName());
        return tbGameCoverMapper.updateTbGameCover(tbGameCover);
    }

    /**
     * 批量删除游戏类型封面
     * 
     * @param ids 需要删除的游戏类型封面主键
     * @return 结果
     */
    @Override
    public int deleteTbGameCoverByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbGameCoverMapper.deleteTbGameCoverByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除游戏类型封面信息
     * 
     * @param id 游戏类型封面主键
     * @return 结果
     */
    @Override
    public int deleteTbGameCoverById(Long id)
    {
        return tbGameCoverMapper.deleteTbGameCoverById(id);
    }
}
