package com.tenant.business.utils;

import com.tenant.common.utils.uuid.UUID;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageUtil {


    public static String saveFile(MultipartFile file, String UPLOAD_DIR, long tenantId) throws IOException {
        // 1. 检查文件是否为空
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传的文件不能为空");
        }

        // 2. 获取并处理文件名（移除路径信息）
        String fileName = UUID.randomUUID() + "_" + tenantId+"."+(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
        String originalFileName = StringUtils.cleanPath(fileName);
        if (originalFileName.contains("..")) {
            throw new IllegalArgumentException("文件名包含非法字符: " + originalFileName);
        }

        // 3. 创建目标目录（如果不存在）
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 4. 构建文件保存路径
        Path filePath = uploadPath.resolve(originalFileName);

        // 5. 检查文件是否已存在（可选：避免覆盖）
        if (Files.exists(filePath)) {
            throw new IOException("文件已存在: " + originalFileName); // 或使用不同文件名保存
        }

        // 6. 将文件保存到磁盘
        file.transferTo(filePath.toFile());
        return fileName;
    }
}