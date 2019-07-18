package com.ins.collect.client;

import com.ins.common.result.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MomentClientTest {
    @Autowired
    MomentClient momentClient;

    @Test
    public void testMq() {
        CommonResult commentsByUserId = momentClient.getMomentByIds("(1,2)");
        System.out.println(commentsByUserId);
    }


}
