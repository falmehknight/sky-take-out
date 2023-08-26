package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName CommonController
 * @Description 通用控制器
 * @Author 谭颍豪
 * @Date 2023/8/26 16:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upLoad(MultipartFile file) {
        log.info("文件上传：{}",file);

        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            String name = UUID.randomUUID().toString() + substring;

            String uploadPath = aliOssUtil.upload(file.getBytes(), name);
            return Result.success(uploadPath);
        } catch (Exception e) {
            log.error("文件上传失败:{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
