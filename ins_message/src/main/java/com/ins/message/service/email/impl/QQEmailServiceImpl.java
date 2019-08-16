package com.ins.message.service.email.impl;

import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.message.constant.EmailChannels;
import com.ins.message.service.email.ChannelEmailService;
import com.ins.message.service.email.EmailService;
import com.ins.model.message.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service(EmailChannels.QQ_EMAIL)
public class QQEmailServiceImpl implements ChannelEmailService {

    private static final Logger logger = LoggerFactory.getLogger(QQEmailServiceImpl.class);

    @Value("${email.qq.sendFrom}")
    private String sendFrom;


    @Autowired
    JavaMailSender javaMailSender;


    @Override
    @SuppressWarnings("unchecked")
    public CommonResult<String> send(Email email) {
        SimpleMailMessage simpleMailMessage = EmailService.buildEmailBody(sendFrom, email.getSendTo(), email.getSubject(), email.getContent());
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            return new CommonResult(CommonCode.FAIL, e.getMessage());
        }
        return new CommonResult(CommonCode.SUCCESS, "发送成功");
    }

    @Override
    public String getChannel() {
        return EmailChannels.QQ_EMAIL;
    }

}
