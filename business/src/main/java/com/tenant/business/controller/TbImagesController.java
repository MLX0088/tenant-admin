package com.tenant.business.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.tenant.business.utils.FileStorageUtil;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.utils.uuid.UUID;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.png.PngImagingParameters;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.business.domain.TbImages;
import com.tenant.business.service.ITbImagesService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

/**
 * 图片管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/business/images")
public class TbImagesController extends BaseController
{
    private String prefix = "business/images";

    @Autowired
    private ITbImagesService tbImagesService;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @RequiresPermissions("business:images:view")
    @GetMapping()
    public String images()
    {
        return prefix + "/images";
    }

    @GetMapping("avatar/{id}")
    public String avatar(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("id", id);
        return prefix + "/avatar";
    }


    @GetMapping("/{id}")
    public String imagesGet(@PathVariable("id") Long id)
    {
        if(id==0){
            return null;
        }
        TbImages tbImages = tbImagesService.selectTbImagesById(id);
        return "redirect:"+tbImages.getUrl();
    }

    @PostMapping("/avatarUpload")
    @ResponseBody
    public String upload_image(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        TbImages tbImages = new TbImages();
//        byte[] b =file.getBytes();
        try {
//            b = compressImage(file);
            String filename = FileStorageUtil.saveFile(file,uploadDir,getTenantId());
            tbImages.setUrl("/images/"+filename);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        tbImages.setImageData(b);
        tbImages.setIsHead(0);
        tbImages.setTenantId(getTenantId());
        tbImages.setUserId(ShiroUtils.getUserId());
        long id = -1;
        try {
            tbImagesService.insertTbImages(tbImages);
            id = tbImages.getId();
        }catch (Exception e){
            e.printStackTrace();
        }

        return id+"";
    }

    @PostMapping("/imageUpload")
    @ResponseBody
    public String upload_image2(@RequestParam("file") MultipartFile file) throws IOException {
        TbImages tbImages = new TbImages();
//        byte[] b =file.getBytes();
        try {
//            b = compressImage(file);
            String filename = FileStorageUtil.saveFile(file,uploadDir,getTenantId());
            tbImages.setUrl("/images/"+filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        tbImages.setImageData(b);
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

        return id+"";
    }

    public TbImages upload_image_inner( MultipartFile file) throws IOException {
        TbImages tbImages = new TbImages();
//        byte[] b =file.getBytes();
        try {
//            b = compressImage(file);

            String filename = FileStorageUtil.saveFile(file,uploadDir,getTenantId());
            tbImages.setUrl("/images/"+filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        tbImages.setImageData(b);
        tbImages.setIsHead(1);
        tbImages.setTenantId(getTenantId());
        long id = -1;
        try {
            tbImagesService.insertTbImages(tbImages);
            id = tbImages.getId();
        }catch (Exception e){
            e.printStackTrace();
        }

        return tbImages;
    }

    /**
     * 查询图片管理列表
     */
    @RequiresPermissions("business:images:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbImages tbImages)
    {
        startPage();
        List<TbImages> list = tbImagesService.selectTbImagesList(tbImages);
        return getDataTable(list);
    }

    /**
     * 导出图片管理列表
     */
    @RequiresPermissions("business:images:export")
    @Log(title = "图片管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbImages tbImages)
    {
        List<TbImages> list = tbImagesService.selectTbImagesList(tbImages);
        ExcelUtil<TbImages> util = new ExcelUtil<TbImages>(TbImages.class);
        return util.exportExcel(list, "图片管理数据");
    }

    /**
     * 新增图片管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图片管理
     */
    @RequiresPermissions("business:images:add")
    @Log(title = "图片管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbImages tbImages)
    {
        return toAjax(tbImagesService.insertTbImages(tbImages));
    }

    /**
     * 修改图片管理
     */
    @RequiresPermissions("business:images:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbImages tbImages = tbImagesService.selectTbImagesById(id);
        mmap.put("tbImages", tbImages);
        return prefix + "/edit";
    }
    /**
     * 详情图片管理
     */
    @RequiresPermissions("business:images:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbImages tbImages = tbImagesService.selectTbImagesById(id);
        return success(tbImages);
    }

    /**
     * 修改保存图片管理
     */
    @RequiresPermissions("business:images:edit")
    @Log(title = "图片管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbImages tbImages)
    {
        return toAjax(tbImagesService.updateTbImages(tbImages));
    }

    /**
     * 删除图片管理
     */
    @RequiresPermissions("business:images:remove")
    @Log(title = "图片管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbImagesService.deleteTbImagesByIds(ids));
    }

    public static byte[] compressImage(MultipartFile file) throws Exception {
        byte[] imageData = file.getBytes();
        BufferedImage image = Imaging.getBufferedImage(imageData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 获取图片格式
        String formatName = Imaging.guessFormat(imageData).getName().toUpperCase();
        if (!formatName.equals("JPEG") && !formatName.equals("PNG")) {
            throw new IllegalArgumentException("仅支持JPEG/PNG格式");
        }

        // 通用压缩参数设置
        float quality = 0.8f;
        int maxSizeBytes = 2 * 1024 * 1024;

        // 渐进式压缩循环
        do {
            outputStream.reset();
            boolean success = compressWithQuality(image, outputStream, formatName, quality);
            if (!success) break;
            quality -= 0.1f;
        } while (outputStream.size() > maxSizeBytes && quality >= 0.1f);

        // 尺寸压缩兜底
        if (outputStream.size() > maxSizeBytes) {
            image = resizeImage(image, 1024, 1024);
            outputStream.reset();
            compressWithQuality(image, outputStream, formatName, 0.5f);
        }

        return outputStream.toByteArray();
    }
    private static boolean compressWithQuality(BufferedImage image,
                                               ByteArrayOutputStream outputStream,
                                               String formatName,
                                               float quality) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);
        if (!writers.hasNext()) return false;

        ImageWriter writer = writers.next();
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream)) {
            writer.setOutput(ios);
            ImageWriteParam param = writer.getDefaultWriteParam();

            // 设置压缩模式
            if (param.canWriteCompressed()) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);

                // JPEG特殊参数
                if (formatName.equalsIgnoreCase("JPEG")) {
                    param.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
                }
            }

            writer.write(null, new IIOImage(image, null, null), param);
        } finally {
            writer.dispose();
        }
        return true;
    }

    private static BufferedImage resizeImage(BufferedImage original, int maxWidth, int maxHeight) {
        int originalWidth = original.getWidth();
        int originalHeight = original.getHeight();
        double ratio = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);

        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        BufferedImage resized = new BufferedImage(newWidth, newHeight,
                original.getTransparency() == Transparency.OPAQUE ?
                        BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resized;
    }

    @GetMapping("/save-all-images")
    @ResponseBody
    public AjaxResult saveAllImagesToDisk() {
        try {
            // 1. 确保目标目录存在
            Path savePath = Paths.get(uploadDir);
            if (!Files.exists(savePath)) {
                Files.createDirectories(savePath);
            }

            // 2. 获取所有图片数据
            List<TbImages> images = tbImagesService.selectTbImagesList(new TbImages()); // 实现这个方法获取所有记录

            if (images == null || images.isEmpty()) {
                return success("没有需要保存的图片数据");
            }

            int successCount = 0;
            // 3. 遍历并保存每张图片
            for (TbImages image : images) {
                image = tbImagesService.selectTbImagesById(image.getId());
                // 生成唯一文件名（使用ID + 扩展名）
                String fileName = UUID.randomUUID() + "_" + image.getTenantId()+".jpg";
                File outputFile = new File(uploadDir + fileName);

                // 写入文件
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(image.getImageData());
                    successCount++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.setUrl("/images/"+fileName);
                tbImagesService.updateTbImages(image);
            }

            return success("图片保存完成。成功: " + successCount + "张，失败: " + (images.size() - successCount));
        } catch (Exception e) {
            return error("处理失败: " + e.getMessage());
        }
    }
}
