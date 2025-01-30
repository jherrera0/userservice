package com.backendchallenge.userservice.application.jwt;

import com.backendchallenge.userservice.domain.exception.authexceptions.MalformJwtException;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import com.backendchallenge.userservice.domain.until.JwtConst;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${app-security-key}")
    private String secretKey = "mySecretKeymysecretkeymySecretKeymysecretkeymySecretKey";

    public String generateToken(String email, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + JwtConst.EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, ConstExceptions.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException(ConstExceptions.TOKEN_UNSUPPORTED);
        } catch (MalformedJwtException e) {
            throw new MalformJwtException();
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ConstExceptions.TOKEN_EMPTY);
        }
    }
}
