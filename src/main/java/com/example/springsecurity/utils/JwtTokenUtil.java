package com.example.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    //jwt: claim -> 내가 원하는 정보를 담아두는 공간.
    public static String createToken(String userName, String key, long expireTimeMs){
        Claims claims = Jwts.claims();
        claims.put("userName", userName); //Token에 claim으로 userName을 넣어줌.

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //발급시간
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //만료시간
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    //토큰 추출 메서드
    public static Claims extractClaims(String token, String secretkey){
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
    }

    //토큰의 만료 여부 체크 메서드
    public static boolean isExpired(String token, String secretKey){
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        return expiredDate.before(new Date()); //expiredDate가 지금보다 전이면 만료된 것
    }

    //토큰으로부터 userName을 꺼내는 메서드
    public static String getUserName(String token, String secretKey){
        return extractClaims(token,secretKey).get("userName", String.class);
    }
}
