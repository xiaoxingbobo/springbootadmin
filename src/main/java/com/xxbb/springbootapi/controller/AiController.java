package com.xxbb.springbootapi.controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeHealthCodeOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeHealthCodeOCRResponse;
import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.Objects;

@RequestMapping("tencent")
@Api(tags = "腾讯Ai")
@RestController
public class AiController {
    static final String SECRET_ID = "AKIDYsxPe65g7IFvfwLt8LXXHzRdZYRte28y";
    static final String SECRET_KEY = "rXAsKWZOnVvGp7D3wI0SgfZeUBH7h19S";
    @Autowired
    private AppConfig appConfig;


    @PostMapping("healthCodeOCR")
    @ApiOperation(value = "健康码识别")
    public JsonResult recognizeHealthCodeOCR(MultipartFile file) {
        try {
            //把图片转换成base64
            BASE64Encoder bEncoder = new BASE64Encoder();
            String[] suffixArra = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            String preffix = "data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
            String base64EncoderImg = preffix + bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
//             实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
//             密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象

            RecognizeHealthCodeOCRRequest req = new RecognizeHealthCodeOCRRequest();
            //图片base64编码
            req.setImageBase64(base64EncoderImg);

            // 返回的resp是一个RecognizeHealthCodeOCRResponse的实例，与请求对象对应
            RecognizeHealthCodeOCRResponse resp = client.RecognizeHealthCodeOCR(req);
            // 输出json格式的字符串回包
            System.out.println(RecognizeHealthCodeOCRResponse.toJsonString(resp));
            return new JsonResultData<>(RecognizeHealthCodeOCRResponse.toJsonString(resp)).Success();

        } catch (Exception e) {
            System.out.println(e.toString());
            return new JsonResult().Fail();
        }

    }

}
