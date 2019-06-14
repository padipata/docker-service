package com.yipage.root.controller;

import com.qiniu.util.Auth;
import com.yipage.root.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 七牛相关操作接口
 * root on 2019/4/26.
 */
@Controller
@Api(tags = "QiniuController", description = "七牛云相关接口")
@RequestMapping("/qiniu")
public class QiniuController {
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.basePath}")
    private String basePath;

    @ApiOperation(value = "获取upToken")
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        Map<String, Object> map = new HashMap<>();
        String upToken = auth.uploadToken(bucket);
        map.put("upToken", upToken);
        map.put("basePath", basePath);
        return CommonResult.success(map);
    }

}
