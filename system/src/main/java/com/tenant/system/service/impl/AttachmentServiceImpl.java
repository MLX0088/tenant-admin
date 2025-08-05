package com.tenant.system.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.system.mapper.AttachmentMapper;
import com.tenant.common.domain.Attachment;
import com.tenant.common.service.IAttachmentService;
import com.tenant.common.core.text.Convert;

/**
 * 附件Service业务层处理
 * 
 * @author luanyu
 * @date 2022-09-19
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService 
{
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    @Override
    public Attachment selectAttachmentById(String id)
    {
        return attachmentMapper.selectAttachmentById(id);
    }

    /**
     * 查询附件列表
     * 
     * @param attachment 附件
     * @return 附件
     */
    @Override
    public List<Attachment> selectAttachmentList(Attachment attachment)
    {
        return attachmentMapper.selectAttachmentList(attachment);
    }

    /**
     * 新增附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    @Override
    public int insertAttachment(Attachment attachment)
    {
        attachment.setCreateTime(DateUtils.getNowDate());
        return attachmentMapper.insertAttachment(attachment);
    }

    /**
     * 修改附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    @Override
    public int updateAttachment(Attachment attachment)
    {
        attachment.setUpdateTime(DateUtils.getNowDate());
        return attachmentMapper.updateAttachment(attachment);
    }

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentByIds(String ids)
    {
        return attachmentMapper.deleteAttachmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除附件信息
     * 
     * @param id 附件主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentById(String id)
    {
        return attachmentMapper.deleteAttachmentById(id);
    }
}
