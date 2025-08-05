package com.tenant.business.controller;

import com.tenant.business.domain.TbImages;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.domain.TbTenantNotice;
import com.tenant.business.service.ITbImagesService;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.business.service.ITbTenantNoticeService;
import com.tenant.business.utils.FileStorageUtil;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 头像管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/v1")
@ResponseBody
public class V1Controller extends BaseController
{

    @Autowired
    private ITbImagesService tbImagesService;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(path="/imageUpload")
    @ResponseBody
    public AjaxResult upload_image3(@RequestParam("file") MultipartFile file) throws IOException {
        TbImages tbImages = new TbImages();
        try {

            String filename = FileStorageUtil.saveFile(file,uploadDir,getTenantId());
            tbImages.setUrl("/images/"+filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tbImages.setIsHead(1);
        tbImages.setTenantId(getTenantId());
        tbImages.setUserId(ShiroUtils.getUserId());
        long id = -1;
        try {
            tbImagesService.insertTbImages(tbImages);
            id = tbImages.getId();
        }catch (Exception e){
            e.printStackTrace();
        }

        return success(id);
    }
}
