package com.ins.message.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云通讯配置
 * @author : hcq
 * @date : 2019/6/17
 */
@Configuration
public class AliSMSConfig {

    @Value("${sms.aliyun.regionId}")
    private String regionId;

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

	@Bean
	public IAcsClient acsClient() throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return  new DefaultAcsClient(profile);
	}

}
