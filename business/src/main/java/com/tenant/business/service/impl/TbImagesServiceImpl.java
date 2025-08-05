package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbImagesMapper;
import com.tenant.business.domain.TbImages;
import com.tenant.business.service.ITbImagesService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 图片管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbImagesServiceImpl implements ITbImagesService 
{
    @Autowired
    private TbImagesMapper tbImagesMapper;

    /**
     * 查询图片管理
     * 
     * @param id 图片管理主键
     * @return 图片管理
     */
    @Override
    public TbImages selectTbImagesById(Long id)
    {
        return tbImagesMapper.selectTbImagesById(id);
    }

    /**
     * 查询图片管理列表
     * 
     * @param tbImages 图片管理
     * @return 图片管理
     */
    @Override
    public List<TbImages> selectTbImagesList(TbImages tbImages)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            tbImages.setCreateBy(sysUser.getLoginName());
        }
        return tbImagesMapper.selectTbImagesList(tbImages);
    }

    /**
     * 新增图片管理
     * 
     * @param tbImages 图片管理
     * @return 结果
     */
    @Override
    public int insertTbImages(TbImages tbImages)
    {
        tbImages.setCreateTime(DateUtils.getNowDate());
        tbImages.setCreateBy(ShiroUtils.getLoginName());
        return tbImagesMapper.insertTbImages(tbImages);
    }

    /**
     * 修改图片管理
     * 
     * @param tbImages 图片管理
     * @return 结果
     */
    @Override
    public int updateTbImages(TbImages tbImages)
    {
        tbImages.setUpdateTime(DateUtils.getNowDate());
         tbImages.setUpdateBy(ShiroUtils.getLoginName());
        return tbImagesMapper.updateTbImages(tbImages);
    }

    /**
     * 批量删除图片管理
     * 
     * @param ids 需要删除的图片管理主键
     * @return 结果
     */
    @Override
    public int deleteTbImagesByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbImagesMapper.deleteTbImagesByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除图片管理信息
     * 
     * @param id 图片管理主键
     * @return 结果
     */
    @Override
    public int deleteTbImagesById(Long id)
    {
        return tbImagesMapper.deleteTbImagesById(id);
    }
}
