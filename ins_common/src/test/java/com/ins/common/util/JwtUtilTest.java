package com.ins.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ins.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.ins.common.util.JwtUtil.generalKey;

/**
 * @author : hcq
 * @date : 2019/8/9
 */
public class JwtUtilTest {

    @Test
    public void testGen() throws Exception {
        //定义payload信息
//        Map<String, Object> tokenMap = new HashMap<>();
//        tokenMap.put("id", "123");
//        tokenMap.put("name", "mrt");
//        tokenMap.put("roles", "r01,r02");
//        tokenMap.put("ext", "1");
        User tokenMap = new User().setId("1").setUsername("sd");
//        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONObject.toJSONString(tokenMap), 11211111L);
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNTU2NWFjNC1iOWQ5LTQzNmQtYmYyMC0zZWRhZjU0ZGE2Y2YiLCJpYXQiOjE1NjU4NTQwODYsInN1YiI6IntcImJpb1wiOlwiaGVsbG9cIixcImVtYWlsXCI6XCIzMDIyQHFxLmNvbVwiLFwiZmFuc051bVwiOjk5OSxcImZvbGxvd051bVwiOjc3NyxcImlkXCI6XCIxXCIsXCJwYXNzd29yZFwiOlwiMTIzNDU2Nzg5XCIsXCJwaG90b1VybFwiOlwiaHR0cHM6Ly9pLmliYi5jby81MTVQa0c2LzIwMTkwNzE1MTQ1MTMzLmpwZ1wiLFwidXBkYXRlVGltZVwiOlwiMjAxOS0wNy0yMlQwMzoyMDoyN1wiLFwidXNlcm5hbWVcIjpcImhjcVwifSIsImV4cCI6MTU2NTg1NDE4Nn0.Fbh7svNeLVcCrr29Hz14mhWzWb4dtgvmM0ibgCsp0B4";
        System.out.println("jwt = " + jwt);
        try {
            SecretKey key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            //将jwt转为Map
            Map m = new HashMap<>();
            m = JSON.parseObject(claims.get("sub").toString(), Map.class);
            System.out.println("m = " + m);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
