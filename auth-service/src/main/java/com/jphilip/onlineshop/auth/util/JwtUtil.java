package com.jphilip.onlineshop.auth.util;

import com.jphilip.onlineshop.auth.entity.Role;
import com.jphilip.onlineshop.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey;

    private final int expirationHours;
    private final int expirationMinutes;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-hours}") int expirationHours,
            @Value("${jwt.expiration-minutes}") int expirationMinutes){

        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationHours = expirationHours;
        this.expirationMinutes = expirationMinutes;
    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("roles", user.getRoles().stream().map(Role::getName).toList())
                .issuedAt(new Date())
                .expiration(generateExpirationDate()) // 10hrs
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date generateExpirationDate() {
        long expirationMillis = Duration.ofHours(expirationHours)
                .plusMinutes(expirationMinutes)
                .toMillis();
        return new Date(System.currentTimeMillis() + expirationMillis);
    }

}
