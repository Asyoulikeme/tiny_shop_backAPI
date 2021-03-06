package cn.wuyuwei.tiny_shop.controller;

import cn.wuyuwei.tiny_shop.common.ApiResultEnum;
import cn.wuyuwei.tiny_shop.common.Result;
import cn.wuyuwei.tiny_shop.config.custom_annotation.LoginRequired;
import cn.wuyuwei.tiny_shop.service.serviceImple.UploadServiceImple;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "文件上传  Actions Controller")
@RequestMapping("/upload")
@RestController
public class UploadController {
    @Autowired
    private UploadServiceImple uploadService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);


    @LoginRequired
    @PostMapping("/avatar")
    @ApiOperation(value = "上传用户头像",notes = "LoginRequired，仅返回路径字符串")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return Result.error(ApiResultEnum.ERROR_IO);
        }

        try {
            String filepath = uploadService.doUploadAvatar(file,request);
            LOGGER.info("上传成功");

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("filepath",filepath);
            return Result.ok(map);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            return Result.error(ApiResultEnum.ERROR_IO);
        }

    }

    @LoginRequired
    @PostMapping("/store-logo")
    @ApiOperation(value = "上传店铺Logo",notes = "LoginRequired，仅返回路径字符串")
    public Result UploadStoreLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()) {
            return Result.error(ApiResultEnum.ERROR_IO);
        }

        try {
            String filepath = uploadService.doUploadStoreLogo(file,request);
            LOGGER.info("上传成功");

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("filepath",filepath);
            return Result.ok(map);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            return Result.error(ApiResultEnum.ERROR_IO);
        }

    }


    @LoginRequired
    @PostMapping("/goods-preview")
    @ApiOperation(value = "上传宝贝预览图",notes = "LoginRequired，仅返回路径字符串")
    public Result UploadGoodsPreviewImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()) {
            return Result.error(ApiResultEnum.ERROR_IO);
        }

        try {
            String filepath = uploadService.doUploadGoodsPreviewImage(file,request);
            LOGGER.info("上传成功");

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("filepath",filepath);
            return Result.ok(map);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            return Result.error(ApiResultEnum.ERROR_IO);
        }
    }

}
