package com.zf.controller;

import com.zf.common.Result;
import com.zf.entity.FileInfo;
import com.zf.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件管理控制器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private MinioService minioService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result<FileInfo> upload(@RequestParam("file") MultipartFile file) {
        FileInfo fileInfo = minioService.uploadFile(file);
        return Result.success("上传成功", fileInfo);
    }

    @ApiOperation("下载文件")
    @GetMapping("/download/{storedName}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String storedName) {
        InputStream inputStream = minioService.downloadFile(storedName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + storedName);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(inputStream));
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/{storedName}")
    public Result<Void> delete(@PathVariable String storedName) {
        minioService.deleteFile(storedName);
        return Result.success("删除成功", null);
    }

    @ApiOperation("获取文件预览URL")
    @GetMapping("/preview/{storedName}")
    public Result<String> getPreviewUrl(
            @PathVariable String storedName,
            @ApiParam("过期时间(天)") @RequestParam(required = false) Integer expiry) {
        String url = minioService.getPreviewUrl(storedName, expiry);
        return Result.success(url);
    }
}
