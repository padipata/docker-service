package com.yipage.root.controller;

import com.yipage.root.common.api.CommonResult;
import com.yipage.root.common.utils.WX.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(tags = "WXPayController", description = "微信支付")
@RestController
@SuppressWarnings("unchecked")
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
    @Value("${wx.uniformorder}")
    private String uniformorder;
    @Value("${wx.notifyUrl}")
    private String notifyUrl;
    @Value("${wx.tradeType}")
    private String tradeType;

    @Autowired
    private HttpServletRequest request;


    /**
     * 获取微信JSAPI支付的请求参数
     */
    @RequestMapping(value = "/jsapiPayParam", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信JSAPI支付的请求参数")
    public CommonResult jsapiPayPrepay() {
        Map resultObj = new TreeMap();

        resultObj.put("appId", appId);
        resultObj.put("timeStamp", System.currentTimeMillis());
        resultObj.put("nonce_str", WXPayUtil.generateNonceStr());
        resultObj.put("signType", "MD5");
        resultObj.put("paySign", WXPayUtil.arraySign(resultObj, paySignKey));

        return CommonResult.success(resultObj);
    }

    /**
     * 获取微信H5支付的请求参数
     */
    @RequestMapping(value = "/h5PayParam", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信H5支付的请求参数")
    public CommonResult h5PayPrepay(String openid) {
        try {
            Map parame = new TreeMap<>();

            parame.put("appid", appId);
            parame.put("mch_id", mchId);
            parame.put("nonce_str", WXPayUtil.generateNonceStr());
            parame.put("notify_url", notifyUrl);
            parame.put("trade_type", tradeType);// 交易类型APP
            // nginx 必须配置好ip
            parame.put("spbill_create_ip", request.getHeader("x-forwarded-for"));
            parame.put("openid", openid);
            parame.put("sign", WXPayUtil.arraySign(parame, paySignKey));
            String xml = WXPayUtil.convertMap2Xml(parame);
            Map<String, Object> resultUn = WXPayUtil.xmlStrToMap(WXPayUtil.requestOnce(uniformorder, xml));
            String prepay_id = WXPayUtil.getString("prepay_id", resultUn);

            // 装配数据
            Map resultObj = new TreeMap<>();
            resultObj.put("package", "prepay_id=" + prepay_id);
            return CommonResult.success(resultObj);

        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("获取参数失败error=" + e.getMessage());
        }
    }

}