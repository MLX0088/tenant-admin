package com.tenant.common.service;

import java.util.List;
import com.tenant.common.domain.Attachment;

/**
 * 附件Service接口
 * 
 * @author luanyu
 * @date 2022-09-19
 */
public interface IAttachmentService 
{
    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    public Attachment selectAttachmentById(String id);

    /**
     * 查询附件列表
     * 
     * @param attachment 附件
     * @return 附件集合
     */
    public List<Attachment> selectAttachmentList(Attachment attachment);

    /**
     * 新增附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    public int insertAttachment(Attachment attachment);

    /**
     * 修改附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    public int updateAttachment(Attachment attachment);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件主键集合
     * @return 结果
     */
    public int deleteAttachmentByIds(String ids);

    /**
     * 删除附件信息
     * 
     * @param id 附件主键
     * @return 结果
     */
    public int deleteAttachmentById(String id);
}
