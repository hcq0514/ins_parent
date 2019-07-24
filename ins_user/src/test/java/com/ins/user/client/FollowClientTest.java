package com.ins.user.client;

import com.ins.model.base.Follow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : hcq
 * @date : 2019/7/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FollowClientTest {
    @Autowired
    MomentClient momentClient;

    @Test
    public  void followClientTest(){
        momentClient.deleteMoment("");
    }

}
