package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbImages;
import org.apache.ibatis.annotations.Param;

/**
 * 图片管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbImagesMapper 
{
    /**
     * 查询图片管理
     * 
     * @param id 图片管理主键
     * @return 图片管理
     */
    public TbImages selectTbImagesById(Long id);

    /**
     * 查询图片管理列表
     * 
     * @param tbImages 图片管理
     * @return 图片管理集合
     */
    public List<TbImages> selectTbImagesList(TbImages tbImages);

    /**
     * 新增图片管理
     * 
     * @param tbImages 图片管理
     * @return 结果
     */
    public int insertTbImages(TbImages tbImages);

    /**
     * 修改图片管理
     * 
     * @param tbImages 图片管理
     * @return 结果
     */
    public int updateTbImages(TbImages tbImages);

    /**
     * 删除图片管理
     * 
     * @param id 图片管理主键
     * @return 结果
     */
    public int deleteTbImagesById(Long id);

    /**
     * 批量删除图片管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbImagesByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
