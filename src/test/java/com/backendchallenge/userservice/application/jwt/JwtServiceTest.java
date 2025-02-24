package com.backendchallenge.userservice.application.jwt;

import com.backendchallenge.userservice.domain.exception.authexceptions.MalformJwtException;
import com.backendchallenge.userservice.domain.until.ConstTest;
import com.backendchallenge.userservice.domain.until.JwtConst;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        jwtService.secretKey = "mySecretKeymysecretkeymySecretKeymysecretkeymySecretKey";
    }

    @Test
    void generateToken_withValidEmailAndClaims_returnsToken() {
        String email = ConstTest.EMAIL_VALID;
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID, ConstTest.ID_TEST);
        extraClaims.put(JwtConst.ROLE, ConstTest.ROLE_NAME_TEST);

        String token = jwtService.generateToken(email, extraClaims);

        assertNotNull(token);
    }

    @Test
    void extractUsername_withValidToken_returnsEmail() {
        String email = ConstTest.EMAIL_VALID;
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID, ConstTest.ID_TEST);
        extraClaims.put(JwtConst.ROLE, ConstTest.ROLE_NAME_TEST);

        String token = jwtService.generateToken(email, extraClaims);
        String extractedEmail = jwtService.extractUsername(token);

        assertEquals(email, extractedEmail);
    }

    @Test
    void extractAllClaims_withValidToken_returnsClaims() {
        String email = ConstTest.EMAIL_VALID;
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID, ConstTest.ID_TEST);
        extraClaims.put(JwtConst.ROLE, ConstTest.ROLE_NAME_TEST);

        String token = jwtService.generateToken(email, extraClaims);
        Claims claims = jwtService.extractAllClaims(token);

        assertEquals(email, claims.getSubject());
        assertEquals(ConstTest.ID_TEST, ((Number) claims.get(JwtConst.ID)).longValue());
        assertEquals(ConstTest.ROLE_NAME_TEST, claims.get(JwtConst.ROLE));
    }

    @Test
    void extractAllClaims_withExpiredToken_throwsExpiredJwtException() {
        String email = ConstTest.EMAIL_VALID;
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID, ConstTest.ID_TEST);
        extraClaims.put(JwtConst.ROLE, ConstTest.ROLE_NAME_TEST);

        String token = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis() - 10000))
                .setExpiration(new Date(System.currentTimeMillis() - 5000))
                .signWith(Keys.hmacShaKeyFor(jwtService.secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        assertThrows(ExpiredJwtException.class, () -> jwtService.extractAllClaims(token));
    }

    @Test
    void extractAllClaims_withMalformedToken_throwsMalformJwtException() {
        String malformedToken = ConstTest.MALFORMED_TOKEN;

        assertThrows(MalformJwtException.class, () -> jwtService.extractAllClaims(malformedToken));
    }

    @Test
    void extractAllClaims_withUnsupportedToken_throwsUnsupportedJwtException() {
        String unsupportedToken = Jwts.builder()
                .setPayload(ConstTest.UNSUPPORTED_TOKEN)
                .signWith(Keys.hmacShaKeyFor(jwtService.secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        assertThrows(UnsupportedJwtException.class, () -> jwtService.extractAllClaims(unsupportedToken));
    }

    @Test
    void extractAllClaims_withEmptyToken_throwsIllegalArgumentException() {
        String emptyToken = ConstTest.EMPTY_TOKEN;

        assertThrows(IllegalArgumentException.class, () -> jwtService.extractAllClaims(emptyToken));
    }
}