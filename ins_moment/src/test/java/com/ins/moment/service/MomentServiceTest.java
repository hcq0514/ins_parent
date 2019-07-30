package com.ins.moment.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : hcq
 * @date : 2019/7/30
 */
public class MomentServiceTest {
    @Test
    public void testPhotoJson(){
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

}
