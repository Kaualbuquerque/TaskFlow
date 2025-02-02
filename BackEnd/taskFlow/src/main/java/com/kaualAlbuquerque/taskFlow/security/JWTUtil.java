package com.kaualAlbuquerque.taskFlow.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        SecretKey key = getKeyBySecret();

        return Jwts.builder()
                .subject(username) // Atualizado: `setSubject()` agora é `subject()`
                .expiration(new Date(System.currentTimeMillis() + this.expiration)) // Correção na criação da data
                .signWith(key, Jwts.SIG.HS256) // Correção: Algoritmo deve ser especificado
                .compact();
    }

    private SecretKey getKeyBySecret() {
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        return key;
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (Objects.nonNull(username) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                return true;
            }
        }

        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims))
            return claims.getSubject();
        return null;
    }

    private Claims getClaims(String token) {
        SecretKey key = getKeyBySecret();
        try {
            return Jwts.parser()
                    .verifyWith(key) // Alterado de setSigningKey() para verifyWith()
                    .build()
                    .parseSignedClaims(token) // Alterado de parseClaimsJws() para parseSignedClaims()
                    .getPayload(); // O corpo do JWT agora é acessado assim
        } catch (Exception e) {
            return null; // Retorna null caso o token seja inválido
        }
    }
}
