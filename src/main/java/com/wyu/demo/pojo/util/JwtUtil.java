package com.wyu.demo.pojo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

        private static String signKey = "wyu666";
        private static Long expire = 43200000L;


        public static String generateJwt(Map<String, Object> claims){
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Long expTime = System.currentTimeMillis() + expire;
            Date exp = new Date(expTime);
            String jwt = Jwts.builder()
                    .addClaims(claims)//自定义信息（有效载荷）
                    .signWith(SignatureAlgorithm.HS256, signKey)//签名算法（头部）
                    .setExpiration(new Date(expTime))//过期时间
                    .compact();
            return jwt;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//指定签名密钥
                .parseClaimsJws(jwt)//指定令牌Token
                .getBody();
        return claims;
    }

    public static Integer getExpire() {
            return expire.intValue();
    }
}
