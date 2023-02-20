package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.JsonResultData;
import com.xxbb.springbootapi.service.impl.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

@Slf4j
@RequestMapping("file")
@Api(tags = "文件操作")
@RestController
public class FileController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private SysUserService userService;


    @PostMapping("upload")
    @ResponseBody
    @ApiOperation(value = "文件上传至服务器")
    public JsonResult upload(MultipartFile file, HttpServletRequest request, String[] dir){
        //通过路径数组合成路径
        String pt = "";
        for (String item : dir) {
            if (!item.isEmpty()) {
                pt += "/" + item;
            }
        }
        String uploadPath = appConfig.getUploadPath(); //D:/upload
        File f = new File(uploadPath);
        if (!f.exists()) {
            f.mkdirs();
        }
//        log.info(pt);
        //获取upload的绝对路径
        String realPath = appConfig.getUploadPath() + pt;
        try {
            //处理文件
            file.transferTo(new File(realPath, Objects.requireNonNull(file.getOriginalFilename())));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JsonResultData<>(appConfig.getUploadPrefix() +"/"+ file.getOriginalFilename()).Success();
    }

    @GetMapping("download/{fileName}")
    @ApiOperation(value = "从服务器下载文件")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response,@PathVariable("fileName") String fileName) throws IOException {
        //获取相对路径
        String realPath = appConfig.getUploadPath();
        //判断是否是文件
        if (!new File(realPath + fileName).isFile()) {
//            throw new LoadException("暂不支持下载文件夹或者当前文件不存在！请勿进行修改代码操作，已记录您本次请求！");
        }
        //获取文件名
        FileInputStream fileInputStream = new FileInputStream(realPath + fileName);
        //设置响应头
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        //流下载
        IOUtils.copy(fileInputStream, outputStream);
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);
        log.info(userService.getCurrentUser().getSysUserInfo().getName() + "下载文件" + fileName);
    }
}
