package com.ins.moment.service;

import com.alibaba.fastjson.JSON;
import com.ins.moment.client.UserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MomentServiceTest {
    @Autowired
    UserClient userClient;

    @Test
    public void testPhotoJson() {
        List<String> photoUrl = new ArrayList<>();
        photoUrl.add("hello1");
        photoUrl.add("hello2");
        photoUrl.add("hello3");
        photoUrl.add("hello4");
        String photo = JSON.toJSONString(photoUrl);
        System.out.println("photo = " + photo);
        List list = JSON.parseObject(photo, List.class);
        System.out.println("list = " + list);
    }


    @Test
    public void testUserClient() {
        userClient.getFansListByUserId("1825");
    }

}
