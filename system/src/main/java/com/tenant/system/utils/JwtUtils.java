package com.tenant.system.utils;

import com.tenant.common.core.domain.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "wEXqaytjolhPNWReUSblmLc8uj1LUVhCvWoGh95mtZYr5G0nLovEi7iARdqvKQFZVhrOujf0XEc4ohR1YNruIw=="; // 密钥
    private static final long EXPIRATION = 86400000; // 24小时

    // 生成 Token
    public static String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 解析 Token
    public static String parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static void main(String[] args) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
            keyGen.init(512); // 明确指定密钥长度为 512 位
            SecretKey secretKey = keyGen.generateKey();
            System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException("生成密钥失败", e);
        }
    }
}