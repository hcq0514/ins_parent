package com.ins.message.service.sms;

import com.ins.message.config.SmsChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ChannelSMSServices implements Iterable<ChannelSmsService> {

    @Autowired
    private SmsChannelConfig smsChannelConfig;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Iterator<ChannelSmsService> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<ChannelSmsService> {

        int i;

        @Override
        public boolean hasNext() {
            return i < smsChannelConfig.getAvailable().size();
        }

        @Override
        public ChannelSmsService next() {
            return (ChannelSmsService) applicationContext.getBean(smsChannelConfig.getAvailable().get(i++));
        }
    }

}
