package com.ins.message.service.sms;


import com.ins.common.result.CommonResult;
import com.ins.model.message.Sms;
import com.ins.model.message.Template;

public interface ChannelSmsService {

    /**
     * 使用模版来发送短信调用具体的短信平台发送短信
     *
     * @param template 消息
     * @param sms
     * @return
     */
    CommonResult<String> sendByTemplate(Template template, Sms sms);

    /**
     * 获取短信渠道名称
     *
     * @return
     */
    String getChannel();

}
