package com.backendchallenge.userservice.infrastructure.configuration.security.filter;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.application.jwt.JwtService;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import com.backendchallenge.userservice.domain.until.ConstRute;
import com.backendchallenge.userservice.domain.until.ConstTest;
import com.backendchallenge.userservice.domain.until.JwtConst;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {


    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtService jwtService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {

        closeable= MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws Exception {
        String token = JwtConst.BEARER+ JwtConst.SPLITERSTRING+ ConstTest.VALID_TOKEN;
        String username = ConstTest.EMAIL_VALID;
        UserEntity userEntity = new UserEntity(ConstTest.ID_TEST,
                ConstTest.EMAIL_VALID,
                ConstTest.PASSWORD_VALID,
                ConstTest.DOCUMENT_VALID,
                ConstTest.PHONE_VALID,
                ConstTest.BIRTHDATE_VALID,
                ConstTest.NAME_VALID,
                ConstTest.LAST_NAME_VALID,
                new RoleEntity(ConstTest.ID_TEST, ConstTest.ROLE_NAME_TEST,new ArrayList<>()));


        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.VALID_TOKEN)).thenReturn(username);
        when(userRepository.findByEmail(username)).thenReturn(userEntity);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(jwtService).extractUsername(ConstTest.VALID_TOKEN);
        verify(userRepository).findByEmail(username);
        verify(filterChain).doFilter(request, response);

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        assert authentication.getPrincipal().equals(userEntity);
    }

    @Test
    void testDoFilterInternal_WithExpiredToken() throws Exception {
        String token = JwtConst.BEARER+ JwtConst.SPLITERSTRING+ConstTest.EXPIRED_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.EXPIRED_TOKEN)).thenThrow(new ExpiredJwtException(null, null, null));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_EXPIRED);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithUnsupportedToken() throws Exception {
        String token = JwtConst.BEARER+ JwtConst.SPLITERSTRING+ConstTest.UNSUPPORTED_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.UNSUPPORTED_TOKEN)).
                thenThrow(new UnsupportedJwtException(ConstExceptions.TOKEN_UNSUPPORTED));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_UNSUPPORTED);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithMalformedToken() throws Exception {
        String token = JwtConst.BEARER+ JwtConst.SPLITERSTRING+ConstTest.MALFORMED_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.MALFORMED_TOKEN)).
                thenThrow(new MalformedJwtException(ConstTest.MALFORMED_TOKEN));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_MALFORMED);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithEmptyToken() throws Exception {
        String token = JwtConst.BEARER + JwtConst.SPLITERSTRING + ConstTest.EMPTY_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.EMPTY_TOKEN)).
                thenThrow(new IllegalArgumentException(ConstExceptions.TOKEN_EMPTY));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, ConstExceptions.TOKEN_EMPTY);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithUserNotFound() throws Exception {
        String token = JwtConst.BEARER + JwtConst.SPLITERSTRING + ConstTest.VALID_TOKEN;
        String username = ConstTest.NOT_FOUND_EMAIL;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);
        when(jwtService.extractUsername(ConstTest.VALID_TOKEN)).thenReturn(username);
        when(userRepository.findByEmail(username)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.USER_NOT_FOUND);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithNoAuthorizationHeader() throws Exception {
        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userRepository, response);
    }
    @Test
    void shouldNotFilter_whenPathIsLogin_returnsTrue() {
        when(request.getRequestURI()).thenReturn(ConstRute.AUTH+ConstRute.LOGIN);

        boolean result = jwtAuthenticationFilter.shouldNotFilter(request);

        assertTrue(result);
    }

    @Test
    void testDoFilterInternal_MissingBearerPrefix() throws Exception {
        String token = ConstTest.VALID_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userRepository, response);
    }

    @Test
    void testDoFilterInternal_MissingSplitString() throws Exception {
        String token = JwtConst.BEARER + ConstTest.VALID_TOKEN;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, ConstExceptions.TOKEN_EMPTY);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_InvalidTokenSplit() throws Exception {
        String token = JwtConst.BEARER + JwtConst.SPLITERSTRING;

        when(request.getHeader(JwtConst.HEADER_STRING)).thenReturn(token);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, ConstExceptions.TOKEN_EMPTY);
        verify(filterChain, never()).doFilter(request, response);
    }
}