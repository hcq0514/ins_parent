package com.ins.message.service.email;


import com.ins.common.result.CommonResult;
import com.ins.model.message.Email;
import com.ins.model.message.Sms;
import com.ins.model.message.Template;
import org.springframework.mail.SimpleMailMessage;

public interface ChannelEmailService {


    CommonResult<String> send(Email email);

    /**
     * 获取短信渠道名称
     *
     * @return
     */
    String getChannel();





}
