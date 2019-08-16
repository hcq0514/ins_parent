package com.ins.message.service.email;


import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.message.constant.MessageStatus;
import com.ins.message.dao.EmailDao;
import com.ins.model.message.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private ChannelEmailServices channelEmailServices;

    public void sendEmail(String sendTo, String subject,String content) {
        try {
            Email email = new Email().setSendTo(sendTo)
                    .setSendStatus(MessageStatus.CREATED)
                    .setSubject(subject)
                    .setContent(content)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
            emailDao.save(email);
            logger.info("存入邮件信息到数据库，email:[{}]", email);
            send(email);
        } catch (Exception e) {
            logger.error("写入message失败,exception:[{}]", e.getMessage());
        }
    }


    private CommonResult send(Email email) {

        logger.info("开始发送邮件:{}", email);
        Iterator<ChannelEmailService> iterator = channelEmailServices.iterator();
        CommonResult<String> result;
        int retryNum = 0;
        String channel;
        do {
            ChannelEmailService channelSMSService = iterator.next();
            channel = channelSMSService.getChannel();
            try {
                result = channelSMSService.send(email);
                break;
            } catch (Exception e) {
                result = new CommonResult(CommonCode.FAIL, e.getMessage());
                if (iterator.hasNext()) {
                    retryNum++;
                }
            }
        } while (iterator.hasNext());

        if (result.isSuccess()) {
            email.setSendStatus(MessageStatus.SUCCESS);
            logger.info("邮件发送成功，email:[{}]", email);
        } else {
            email.setSendStatus(MessageStatus.FAILED);
            logger.info("邮件发送失败，emailId:[{}],e:[{}]", email.getId(), result.getData());
        }
        email.setRetry(retryNum);
        email.setResponse(result.getData());
        email.setChannel(channel);
        email.setUpdateTime(LocalDateTime.now());
        emailDao.save(email);
        return result;
    }

    public static SimpleMailMessage buildEmailBody(String sendFrom, String sendTo, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送人
        simpleMailMessage.setFrom(sendFrom);
        //邮件接收人
        simpleMailMessage.setTo(sendTo);
        //邮件主题
        simpleMailMessage.setSubject(subject);
        //邮件内容
        simpleMailMessage.setText(content);
        return simpleMailMessage;
    }
}


