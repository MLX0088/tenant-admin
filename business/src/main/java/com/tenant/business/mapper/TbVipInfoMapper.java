package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbVipInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 会员信息Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbVipInfoMapper 
{
    /**
     * 查询会员信息
     * 
     * @param id 会员信息主键
     * @return 会员信息
     */
    public TbVipInfo selectTbVipInfoById(Long id);

    /**
     * 查询会员信息列表
     * 
     * @param tbVipInfo 会员信息
     * @return 会员信息集合
     */
    public List<TbVipInfo> selectTbVipInfoList(TbVipInfo tbVipInfo);

    /**
     * 新增会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    public int insertTbVipInfo(TbVipInfo tbVipInfo);

    /**
     * 修改会员信息
     * 
     * @param tbVipInfo 会员信息
     * @return 结果
     */
    public int updateTbVipInfo(TbVipInfo tbVipInfo);

    /**
     * 删除会员信息
     * 
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteTbVipInfoById(Long id);

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbVipInfoByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
