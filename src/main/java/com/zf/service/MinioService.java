package com.zf.service;

import com.zf.config.MinioConfig;
import com.zf.entity.FileInfo;
import com.zf.exception.BusinessException;
import com.zf.mapper.FileInfoMapper;
import com.zf.utils.UserHolder;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * MinIO文件服务
 * 
 * @author zf
 * @since 2026-01-01
 */
@Slf4j
@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 上传文件
     */
    public FileInfo uploadFile(MultipartFile file) {
        try {
            // 确保桶存在
            ensureBucketExists();

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".") 
                    ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String storedName = UUID.randomUUID().toString() + extension;

            // 上传文件
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(storedName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // 保存文件信息到数据库
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(originalFilename);
            fileInfo.setStoredName(storedName);
            fileInfo.setFilePath(minioConfig.getBucketName() + "/" + storedName);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(extension.replaceFirst("\\.", ""));
            fileInfo.setMimeType(file.getContentType());
            fileInfo.setStorageType("MINIO");
            fileInfo.setUploaderId(UserHolder.getUserId());
            fileInfo.setBucketName(minioConfig.getBucketName());
            fileInfoMapper.insert(fileInfo);

            return fileInfo;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new BusinessException("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 下载文件
     */
    public InputStream downloadFile(String storedName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(storedName)
                            .build()
            );
        } catch (Exception e) {
            log.error("文件下载失败", e);
            throw new BusinessException("文件下载失败: " + e.getMessage());
        }
    }

    /**
     * 删除文件
     */
    public void deleteFile(String storedName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(storedName)
                            .build()
            );
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new BusinessException("文件删除失败: " + e.getMessage());
        }
    }

    /**
     * 获取文件预览URL
     */
    public String getPreviewUrl(String storedName, Integer expiry) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(storedName)
                            .method(Method.GET)
                            .expiry(expiry != null ? expiry : 7, TimeUnit.DAYS)
                            .build()
            );
        } catch (Exception e) {
            log.error("获取文件预览URL失败", e);
            throw new BusinessException("获取文件预览URL失败: " + e.getMessage());
        }
    }

    /**
     * 确保桶存在
     */
    private void ensureBucketExists() {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .build()
            );
            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(minioConfig.getBucketName())
                                .build()
                );
            }
        } catch (Exception e) {
            log.error("检查桶是否存在失败", e);
            throw new BusinessException("检查桶是否存在失败: " + e.getMessage());
        }
    }
}
