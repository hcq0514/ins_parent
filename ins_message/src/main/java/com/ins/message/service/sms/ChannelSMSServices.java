package com.ins.message.service.sms;

import com.ins.message.config.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ChannelSMSServices implements Iterable<ChannelSmsService> {

    @Autowired
    private ChannelConfig channelConfig;

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
            return i < channelConfig.getAvailable().size();
        }

        @Override
        public ChannelSmsService next() {
            return (ChannelSmsService) applicationContext.getBean(channelConfig.getAvailable().get(i++));
        }
    }

}
