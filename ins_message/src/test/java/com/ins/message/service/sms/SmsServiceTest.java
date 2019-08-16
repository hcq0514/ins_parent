package com.ins.message.service.sms;

import com.ins.common.mq.RabbitMQCode;
import com.ins.message.service.email.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Test
    public void testMq() {
        Map map = new HashMap();
        map.put("mobile", "13113051027");
        map.put("code", "123456");
        rabbitTemplate.convertAndSend(RabbitMQCode.USER_EXCHANGE, RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY, map);
    }

    @Test
    public void testEmail() {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom("302827797@qq.com");
            //邮件接收人
            simpleMailMessage.setTo("hcq0514@163.com");
            //邮件主题
            simpleMailMessage.setSubject("hello");
            //邮件内容
            simpleMailMessage.setText("test");
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Test
    public void testEmailService() {
        try {
            emailService.sendEmail("hcq0514@163.com","伍俊锋傻狗","伍俊锋傻狗");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }


}
