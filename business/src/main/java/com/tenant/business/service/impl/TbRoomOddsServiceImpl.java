package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbRoomOddsMapper;
import com.tenant.business.domain.TbRoomOdds;
import com.tenant.business.service.ITbRoomOddsService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 房间赔率Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-06
 */
@Service
public class TbRoomOddsServiceImpl implements ITbRoomOddsService 
{
    @Autowired
    private TbRoomOddsMapper tbRoomOddsMapper;

    /**
     * 查询房间赔率
     * 
     * @param id 房间赔率主键
     * @return 房间赔率
     */
    @Override
    public TbRoomOdds selectTbRoomOddsById(Long id)
    {
        return tbRoomOddsMapper.selectTbRoomOddsById(id);
    }

    /**
     * 查询房间赔率列表
     * 
     * @param tbRoomOdds 房间赔率
     * @return 房间赔率
     */
    @Override
    public List<TbRoomOdds> selectTbRoomOddsList(TbRoomOdds tbRoomOdds)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbRoomOdds.setCreateBy(sysUser.getLoginName());
        }
        return tbRoomOddsMapper.selectTbRoomOddsList(tbRoomOdds);
    }

    /**
     * 新增房间赔率
     * 
     * @param tbRoomOdds 房间赔率
     * @return 结果
     */
    @Override
    public int insertTbRoomOdds(TbRoomOdds tbRoomOdds)
    {
        tbRoomOdds.setCreateTime(DateUtils.getNowDate());
        tbRoomOdds.setCreateBy(ShiroUtils.getLoginName());
        return tbRoomOddsMapper.insertTbRoomOdds(tbRoomOdds);
    }

    /**
     * 修改房间赔率
     * 
     * @param tbRoomOdds 房间赔率
     * @return 结果
     */
    @Override
    public int updateTbRoomOdds(TbRoomOdds tbRoomOdds)
    {
        tbRoomOdds.setUpdateTime(DateUtils.getNowDate());
         tbRoomOdds.setUpdateBy(ShiroUtils.getLoginName());
        return tbRoomOddsMapper.updateTbRoomOdds(tbRoomOdds);
    }

    /**
     * 批量删除房间赔率
     * 
     * @param ids 需要删除的房间赔率主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomOddsByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbRoomOddsMapper.deleteTbRoomOddsByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除房间赔率信息
     * 
     * @param id 房间赔率主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomOddsById(Long id)
    {
        return tbRoomOddsMapper.deleteTbRoomOddsById(id);
    }
}
