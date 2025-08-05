package com.tenant.common.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import com.tenant.common.utils.ServletUtils;
import com.tenant.common.utils.spring.SpringUtils;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

/**
 * Minio 文件存储工具类
 * 
 * @author tenant
 */
public class MinioUtil
{
    /**
     * 上传文件
     * 
     * @param bucketName 桶名称
     * @param fileName
     * @throws IOException
     */
    public static String uploadFile(String bucketName, String fileName, MultipartFile multipartFile) throws IOException
    {
        String url = "";
        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
        try (InputStream inputStream = multipartFile.getInputStream())
        {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(inputStream, multipartFile.getSize(), -1).contentType(multipartFile.getContentType()).build());
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(fileName).method(Method.GET).build());
            url = url.substring(0, url.indexOf('?'));
            return ServletUtils.urlDecode(url);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 删除对象
     * @param bucketName
     * @param objectName
     * @throws IOException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws InsufficientDataException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws InternalException
     * @throws XmlParserException
     * @throws ErrorResponseException
     */
    public   static void remove(String bucketName,String objectName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
