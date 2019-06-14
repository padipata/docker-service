package com.yipage.root.controller;

import com.yipage.root.common.api.CommonResult;
import com.yipage.root.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "WXPayController", description = "微信支付")
@RestController
@RequestMapping("/api/pay")
public class WXPayController {
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.mchId}")
    private String mchId;
    @Value("${wx.paySignKey}")
    private String paySignKey;

    /**
     * 获取微信支付的请求参数
     */
    @RequestMapping(value = "/payParam", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信支付的请求参数")
    public CommonResult payPrepay() {
        Map resultObj = new TreeMap();

        resultObj.put("appId", appId);
        resultObj.put("timeStamp", System.currentTimeMillis());
        resultObj.put("nonce_str", WXPayUtil.generateNonceStr());
        resultObj.put("signType", "MD5");
        resultObj.put("paySign", WXPayUtil.arraySign(resultObj, paySignKey));

        return CommonResult.success(resultObj);
    }

}