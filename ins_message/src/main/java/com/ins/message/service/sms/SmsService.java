package com.ins.message.service.sms;


import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.message.constant.SmsStatus;
import com.ins.message.dao.SmsDao;
import com.ins.message.dao.TemplateDao;
import com.ins.model.message.Sms;
import com.ins.model.message.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);


    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private SmsDao smsDao;

    @Autowired
    private ChannelSMSServices channelSMSServices;

    public void sendSmsByTemplate(String mobile, String templateId, boolean isCaptcha, String params) {
        try {
            Sms sms = new Sms()
                    .setMobile(mobile)
                    .setTemplateId(templateId)
                    .setSendStatus(SmsStatus.CREATED)
                    .setParams(params)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
            smsDao.save(sms);
            logger.info("写入消息进阻塞队列，sms:[{}]", sms);
            send(sms);
        } catch (Exception e) {
            logger.error("写入message失败,exception:[{}]", e.getMessage());
        }
    }


    private CommonResult send(Sms sms) {

        logger.info("开始发送短信:{}", sms);
        Iterator<ChannelSmsService> iterator = channelSMSServices.iterator();
        CommonResult<String> result;
        int retryNum = 0;
        String channel;
        do {
            ChannelSmsService channelSMSService = iterator.next();
            channel = channelSMSService.getChannel();
            try {
                Optional<Template> templateOptional = templateDao.findById(sms.getTemplateId());
                result = channelSMSService.sendByTemplate(templateOptional.get(), sms);
                break;
            } catch (Exception e) {
                result = new CommonResult(CommonCode.FAIL, e.getMessage());
                if (iterator.hasNext()) {
                    retryNum++;
                }
            }
        } while (iterator.hasNext());

        if (result.isSuccess()) {
            sms.setSendStatus(SmsStatus.SUCCESS);
            logger.info("短信发送成功，sms:[{}]", sms);
        } else {
            sms.setSendStatus(SmsStatus.FAILED);
            logger.info("短信发送失败，smsId:[{}],e:[{}]", sms.getId(), result.getData());
        }
        sms.setRetry(retryNum);
        sms.setResponse(result.getData());
        sms.setChannel(channel);
        sms.setUpdateTime(LocalDateTime.now());
        smsDao.save(sms);
        return result;
    }
}


