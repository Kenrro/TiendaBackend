package com.ecomerce.ecomerce.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final Key SECRECT_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generatedToken(User user) {
        return getToken(new HashMap<>(), user);
    }
    public String getToken(Map<String, Object> extraClaims, User user) {
        extraClaims.put("username", user.getUsername());
        extraClaims.put("firstname", user.getFirstname());
        extraClaims.put("lastname", user.getLastname());
        extraClaims.put("role", user.getRole().name());
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
            .signWith(SECRECT_KEY, SignatureAlgorithm.HS256)
            .compact();
    }
    // private Key getKey() { 
    //     byte[] keyBytes = Decoders.BASE64.decode(SECRECT_KEY);
    //     return Keys.hmacShaKeyFor(keyBytes);
    // }
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);  
    }   
    private Claims getAllClaims(String token){

        return Jwts.parserBuilder()
            .setSigningKey(SECRECT_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
