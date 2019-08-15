package com.ins.common.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author : hcq
 * @date : 2019/8/9
 */
public class JwtUtilTest {

    @Test
    public void testGen() throws Exception {
        //定义payload信息
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", "123");
        tokenMap.put("name", "mrt");
        tokenMap.put("roles", "r01,r02");
        tokenMap.put("ext", "1");
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONObject.toJSONString(tokenMap), 11211111L);
        System.out.println("jwt = " + jwt);


    }

}
