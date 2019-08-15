package com.ins.message.controller;


import com.ins.message.service.sms.SmsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : hcq
 * @date : 2019/6/17
 */

@Controller
@RequestMapping("sms")
public class SmsController {

    @Autowired
    SmsService smsService;

    @ApiOperation("根据短信模版发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, example = "13113051027", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "templateId", value = "模版id", required = true, example = "1", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "captcha", value = "是否为验证码", example = "true", paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "params", value = "参数", example = "{\"code\":\"1442\",\"phone\":\"13113051027\"}", paramType = "query", dataType = "string")
    })
    @PostMapping("sendSmsByTemplate")
    @ResponseBody
    public String sendSmsByTemplate(@RequestParam("mobile") String mobile, @RequestParam("templateId") String templateId,
                                    @RequestParam("captcha") boolean isCaptcha, @RequestParam("params") String params) {
        smsService.sendSmsByTemplate(mobile, templateId, isCaptcha, params);
        return "写入短信队列成功";
    }

}
