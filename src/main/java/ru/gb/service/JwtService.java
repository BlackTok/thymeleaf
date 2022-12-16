package ru.gb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.gb.config.JwtProperties;

import java.util.*;

@Service
public class JwtService {
    @Autowired
    private JwtProperties properties;

    public String generateJwtToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>(Map.of("authority", user.getAuthorities()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public String getUsername(String bearerTokenValue) {
        return parse(bearerTokenValue).getSubject();
    }

    public List<GrantedAuthority> getAuthority(String bearerTokenValue) {
        List<String> authority = (List<String>) parse(bearerTokenValue).get("authority");

        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }
}
