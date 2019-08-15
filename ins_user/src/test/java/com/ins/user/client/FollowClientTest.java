package com.ins.user.client;

import com.alibaba.fastjson.JSONObject;
import com.ins.common.util.JwtUtil;
import com.ins.model.base.Follow;
import com.ins.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
    public void followClientTest(){
        momentClient.deleteMoment("");
    }

    @Test
    public void testJwt(){
        User user = new User().setUsername("hcq");
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONObject.toJSONString(user), 100000);
        System.out.println("token = " + token);
    }

}
