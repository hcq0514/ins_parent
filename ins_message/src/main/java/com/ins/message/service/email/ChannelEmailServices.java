package com.ins.message.service.email;

import com.ins.message.config.EmailChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ChannelEmailServices implements Iterable<ChannelEmailService> {

    @Autowired
    private EmailChannelConfig emailChannelConfig;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Iterator<ChannelEmailService> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<ChannelEmailService> {

        int i;

        @Override
        public boolean hasNext() {
            return i < emailChannelConfig.getAvailable().size();
        }

        @Override
        public ChannelEmailService next() {
            return (ChannelEmailService) applicationContext.getBean(emailChannelConfig.getAvailable().get(i++));
        }
    }

}
