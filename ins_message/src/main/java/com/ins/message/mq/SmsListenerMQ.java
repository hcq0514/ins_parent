package com.ins.message.mq;

import com.alibaba.fastjson.JSON;
import com.ins.common.mq.RabbitMQCode;
import com.ins.common.sms.SmsTemplate;
import com.ins.message.dao.TemplateDao;
import com.ins.message.service.sms.SmsService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hcq
 */
@Component
public class SmsListenerMQ {
    @Autowired
    SmsService smsService;

    @Autowired
    TemplateDao templateDao;

    /**
     * 验证码发送
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQCode.USER_QUEUE, durable = "true"),
            exchange = @Exchange(
                    value = RabbitMQCode.USER_EXCHANGE,
                    ignoreDeclarationExceptions = "true"
            ),
            key = RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY
    ))
    public void sendVerificationCode(Map map) {
        String mobile = (String) map.get("mobile");
        Map<String, Object> para = new HashMap<>(3);
        para.put("code", map.get("code"));
        String templateId = templateDao.getByChannelTemplateId(SmsTemplate.ALIYUN_VERIFY_CHANNEL_TEMPLATE_ID).getId();
        smsService.sendSmsByTemplate(mobile, templateId, true, JSON.toJSONString(para));
    }
}
