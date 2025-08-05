package com.tenant.system.controller;

import java.io.IOException;
import java.util.List;

import com.tenant.common.config.TenantConfig;
import com.tenant.common.constant.Constants;
import com.tenant.common.utils.StringUtils;
import com.tenant.common.utils.file.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.domain.Attachment;
import com.tenant.common.service.IAttachmentService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件Controller
 * 
 * @author luanyu
 * @date 2022-09-19
 */
@Controller
@RequestMapping("/system/attachment")
public class AttachmentController extends BaseController
{
    private String prefix = "system/attachment";

    @Autowired
    private IAttachmentService attachmentService;

    @RequiresPermissions("system:attachment:view")
    @GetMapping()
    public String attachment()
    {
        return prefix + "/attachment";
    }
    /**
     * 新增保存附件
     */
    @RequiresPermissions("system:attachment:add")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Attachment attachment, MultipartFile file)
    {
        if (!file.isEmpty()) {
            try {
                attachment.setName(file.getOriginalFilename());
                attachment.setType(FileUploadUtils.getExtension(file));
                // 本地资源路径
                String localPath = TenantConfig.getProfile();
                // 数据库资源地址
                String path = localPath + StringUtils.substringAfter(FileUploadUtils.upload(file), Constants.RESOURCE_PREFIX);
                attachment.setPath(path);
                attachment.setNewName(attachment.getPath().substring(attachment.getPath().lastIndexOf("/")+1));
                attachment.setSize(file.getSize());
                ;
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return toAjax(attachmentService.insertAttachment(attachment));
    }
    /**
     * 查询附件列表
     */
    @RequiresPermissions("system:attachment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Attachment attachment)
    {
        startPage();
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @RequiresPermissions("system:attachment:export")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Attachment attachment)
    {
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        ExcelUtil<Attachment> util = new ExcelUtil<Attachment>(Attachment.class);
        return util.exportExcel(list, "附件数据");
    }

    /**
     * 新增附件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    /**
     * 修改附件
     */
    @RequiresPermissions("system:attachment:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Attachment attachment = attachmentService.selectAttachmentById(id);
        mmap.put("attachment", attachment);
        return prefix + "/edit";
    }

    /**
     * 修改保存附件
     */
    @RequiresPermissions("system:attachment:edit")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Attachment attachment)
    {
        attachment.setUpdateBy(getLoginName());
        return toAjax(attachmentService.updateAttachment(attachment));
    }

    /**
     * 删除附件
     */
    @RequiresPermissions("system:attachment:remove")
    @Log(title = "附件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(attachmentService.deleteAttachmentByIds(ids));
    }
}
