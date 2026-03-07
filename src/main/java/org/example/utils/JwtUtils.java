package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final String SECRET = "your_very_secret_key_123456"; // 密钥
    private static final long EXPIRE = 60 * 60 * 3 * 1000;

    public String createToken(Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserRole(String token) {
        Claims claims = parseToken(token);
        return (String) claims.get("role");
    }

    public Long getUserId(String token) {
        Claims claims = parseToken(token);
        return (Long) claims.get("userId");
    }

    public Boolean isTokenExpired(String token) {
        try{
            return parseToken(token)
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e){
            return true;
        }
    }
}
