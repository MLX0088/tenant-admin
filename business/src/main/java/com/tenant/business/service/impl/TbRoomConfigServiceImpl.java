package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbRoomConfigMapper;
import com.tenant.business.domain.TbRoomConfig;
import com.tenant.business.service.ITbRoomConfigService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 房间Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbRoomConfigServiceImpl implements ITbRoomConfigService 
{
    @Autowired
    private TbRoomConfigMapper tbRoomConfigMapper;

    /**
     * 查询房间
     * 
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public TbRoomConfig selectTbRoomConfigById(Long id)
    {
        return tbRoomConfigMapper.selectTbRoomConfigById(id);
    }

    /**
     * 查询房间列表
     * 
     * @param tbRoomConfig 房间
     * @return 房间
     */
    @Override
    public List<TbRoomConfig> selectTbRoomConfigList(TbRoomConfig tbRoomConfig)
    {

        return tbRoomConfigMapper.selectTbRoomConfigList(tbRoomConfig);
    }

    /**
     * 新增房间
     * 
     * @param tbRoomConfig 房间
     * @return 结果
     */
    @Override
    public int insertTbRoomConfig(TbRoomConfig tbRoomConfig)
    {
        tbRoomConfig.setCreateTime(DateUtils.getNowDate());
        tbRoomConfig.setCreateBy(ShiroUtils.getLoginName());
        return tbRoomConfigMapper.insertTbRoomConfig(tbRoomConfig);
    }

    /**
     * 修改房间
     * 
     * @param tbRoomConfig 房间
     * @return 结果
     */
    @Override
    public int updateTbRoomConfig(TbRoomConfig tbRoomConfig)
    {
        tbRoomConfig.setUpdateTime(DateUtils.getNowDate());
         tbRoomConfig.setUpdateBy(ShiroUtils.getLoginName());
        return tbRoomConfigMapper.updateTbRoomConfig(tbRoomConfig);
    }

    /**
     * 批量删除房间
     * 
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomConfigByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbRoomConfigMapper.deleteTbRoomConfigByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除房间信息
     * 
     * @param id 房间主键
     * @return 结果
     */
    @Override
    public int deleteTbRoomConfigById(Long id)
    {
        return tbRoomConfigMapper.deleteTbRoomConfigById(id);
    }
}
