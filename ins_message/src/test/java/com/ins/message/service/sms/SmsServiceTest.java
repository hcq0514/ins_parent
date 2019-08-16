package com.ins.message.service.sms;

import com.ins.common.mq.RabbitMQCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hcq
 * @date : 2019/8/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsServiceTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testMq() {
        Map map = new HashMap();
        map.put("mobile","13113051027");
        map.put("code","123456");
        rabbitTemplate.convertAndSend(RabbitMQCode.USER_EXCHANGE, RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY, map);
    }


}
