package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbTenantNoticeMapper;
import com.tenant.business.domain.TbTenantNotice;
import com.tenant.business.service.ITbTenantNoticeService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 租户公告管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-08
 */
@Service
public class TbTenantNoticeServiceImpl implements ITbTenantNoticeService 
{
    @Autowired
    private TbTenantNoticeMapper tbTenantNoticeMapper;

    /**
     * 查询租户公告管理
     * 
     * @param id 租户公告管理主键
     * @return 租户公告管理
     */
    @Override
    public TbTenantNotice selectTbTenantNoticeById(Long id)
    {
        return tbTenantNoticeMapper.selectTbTenantNoticeById(id);
    }

    /**
     * 查询租户公告管理列表
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 租户公告管理
     */
    @Override
    public List<TbTenantNotice> selectTbTenantNoticeList(TbTenantNotice tbTenantNotice)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbTenantNotice.setCreateBy(sysUser.getLoginName());
        }
        return tbTenantNoticeMapper.selectTbTenantNoticeList(tbTenantNotice);
    }

    /**
     * 新增租户公告管理
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 结果
     */
    @Override
    public int insertTbTenantNotice(TbTenantNotice tbTenantNotice)
    {
        tbTenantNotice.setCreateTime(DateUtils.getNowDate());
        tbTenantNotice.setCreateBy(ShiroUtils.getLoginName());
        return tbTenantNoticeMapper.insertTbTenantNotice(tbTenantNotice);
    }

    /**
     * 修改租户公告管理
     * 
     * @param tbTenantNotice 租户公告管理
     * @return 结果
     */
    @Override
    public int updateTbTenantNotice(TbTenantNotice tbTenantNotice)
    {
        tbTenantNotice.setUpdateTime(DateUtils.getNowDate());
         tbTenantNotice.setUpdateBy(ShiroUtils.getLoginName());
        return tbTenantNoticeMapper.updateTbTenantNotice(tbTenantNotice);
    }

    /**
     * 批量删除租户公告管理
     * 
     * @param ids 需要删除的租户公告管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTenantNoticeByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbTenantNoticeMapper.deleteTbTenantNoticeByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除租户公告管理信息
     * 
     * @param id 租户公告管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTenantNoticeById(Long id)
    {
        return tbTenantNoticeMapper.deleteTbTenantNoticeById(id);
    }
}
