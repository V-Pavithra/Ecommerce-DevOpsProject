package com.ecommerce.user.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    //@Value("${jwt.secret}")
    //private String secret;
   // private final String secret = System.getenv("JWT_SECRET");

//    public String generateToken(String email) {
//        if (secret == null || secret.isEmpty()) {
//            throw new IllegalStateException("JWT_SECRET environment variable is not set!");
//        }

    public JwtUtil(@Value("${jwt.secret}") String secret) {
            byte[] keyBytes = Base64.getDecoder().decode(secret);  // Proper Base64 decoding
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey, SignatureAlgorithm.HS256) // Use a secure key
                .compact();
    }

//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();

}
