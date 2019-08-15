package com.ins.common.util;

import com.alibaba.fastjson.JSON;
import com.ins.model.user.UserJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author : hcq
 * @date : 2019/8/9
 */
public class JwtUtil {

    private static String keyName = "hcq";

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(String id, String subject, long ttlMillis) {

        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            SecretKey key = generalKey();
            JwtBuilder builder = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .signWith(signatureAlgorithm, key);
            if (ttlMillis >= 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }
            return builder.compact();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public static Map<String, String> getJwtClaimsFromHeader(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        //取出头信息
        String token = request.getHeader("Bearer");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Map<String, String> map = null;
        try {
            SecretKey key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
            //将jwt转为Map
            map = JSON.parseObject(claims.get("sub").toString(), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static UserJwt getUserJwtFromHeader(HttpServletRequest request) {
        Map<String, String> jwtClaims = getJwtClaimsFromHeader(request);
        if (jwtClaims == null || StringUtils.isEmpty(jwtClaims.get("id"))) {
            return null;
        }
        UserJwt userJwt = new UserJwt();
        userJwt.setId(jwtClaims.get("id"));
        userJwt.setUsername(jwtClaims.get("username"));
        userJwt.setBio(jwtClaims.get("bio"));
        userJwt.setEmail(jwtClaims.get("email"));
        userJwt.setPhotoUrl(jwtClaims.get("photoUrl"));
        return userJwt;
    }


    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = keyName + Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


}
