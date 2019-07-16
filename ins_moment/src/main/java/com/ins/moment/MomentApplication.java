package com.ins.moment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.ins.*")
@EntityScan("com.ins.model.*")
@EnableSwagger2
public class MomentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomentApplication.class);
    }



}
