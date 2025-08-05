package com.tenant.business.mapper;

import java.util.List;
import com.tenant.business.domain.TbQuickReply;
import org.apache.ibatis.annotations.Param;

/**
 * 快捷语管理Mapper接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface TbQuickReplyMapper 
{
    /**
     * 查询快捷语管理
     * 
     * @param id 快捷语管理主键
     * @return 快捷语管理
     */
    public TbQuickReply selectTbQuickReplyById(Long id);

    /**
     * 查询快捷语管理列表
     * 
     * @param tbQuickReply 快捷语管理
     * @return 快捷语管理集合
     */
    public List<TbQuickReply> selectTbQuickReplyList(TbQuickReply tbQuickReply);

    /**
     * 新增快捷语管理
     * 
     * @param tbQuickReply 快捷语管理
     * @return 结果
     */
    public int insertTbQuickReply(TbQuickReply tbQuickReply);

    /**
     * 修改快捷语管理
     * 
     * @param tbQuickReply 快捷语管理
     * @return 结果
     */
    public int updateTbQuickReply(TbQuickReply tbQuickReply);

    /**
     * 删除快捷语管理
     * 
     * @param id 快捷语管理主键
     * @return 结果
     */
    public int deleteTbQuickReplyById(Long id);

    /**
     * 批量删除快捷语管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbQuickReplyByIds(@Param("ids")String[] ids,@Param("createBy") String createBy);
}
