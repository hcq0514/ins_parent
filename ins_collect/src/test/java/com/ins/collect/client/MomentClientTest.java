package com.ins.collect.client;

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
        momentClient.getCommentsByUserId("5a795ac7dd573c04508f3a56");
        System.out.println();
    }


}
