package com.ins.message.service.sms.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.message.constant.SmsChannels;
import com.ins.message.service.sms.ChannelSmsService;
import com.ins.model.message.Sms;
import com.ins.model.message.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(SmsChannels.ALI_SMS)
public class AliSmsServiceImpl implements ChannelSmsService {

    private static final Logger logger = LoggerFactory.getLogger(AliSmsServiceImpl.class);

    @Value("${sms.aliyun.regionId}")
    private String regionId;

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Autowired
    private IAcsClient client;


    @Override
    @SuppressWarnings("unchecked")
    public CommonResult<String> sendByTemplate(Template template, Sms sms) {

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", sms.getMobile());
        request.putQueryParameter("SignName", "cq");
        request.putQueryParameter("TemplateCode", template.getChannelTemplateId());
        request.putQueryParameter("TemplateParam", sms.getParams());
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            return new CommonResult(CommonCode.FAIL, e.getMessage());
        }
        if (Objects.requireNonNull(response).getHttpResponse().isSuccess()) {
            return new CommonResult(CommonCode.SUCCESS, response.getData());
        } else {
            return new CommonResult(CommonCode.FAIL, response.getData());
        }
    }

    @Override
    public String getChannel() {
        return SmsChannels.ALI_SMS;
    }

}
