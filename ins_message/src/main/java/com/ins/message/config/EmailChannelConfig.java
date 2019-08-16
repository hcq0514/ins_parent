package com.ins.message.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 短信渠道配置
 */
@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailChannelConfig {

    private List<String> available;

    public void setAvailable(List<String> available) {
        this.available = available;
    }

    public List<String> getAvailable() {
        return available;
    }

}
