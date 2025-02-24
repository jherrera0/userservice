package com.backendchallenge.userservice.infrastructure.configuration.security.filter;

import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.application.jwt.JwtService;
import com.backendchallenge.userservice.domain.until.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(JwtConst.HEADER_STRING);

        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtConst.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!authorizationHeader.contains(JwtConst.SPLITERSTRING) ||
                authorizationHeader.split(JwtConst.SPLITERSTRING).length < ConstValidation.TWO) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ConstExceptions.TOKEN_EMPTY);
            return;
        }


        String jwt = authorizationHeader.split(JwtConst.SPLITERSTRING)[1];

        try {
            String username = jwtService.extractUsername(jwt);
            UserEntity user = userRepository.findByEmail(username);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.USER_NOT_FOUND);
                return;
            }

            UserDetails userDetails = user;
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, ConstTest.EMPTY_STRING, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_EXPIRED);
            return;
        } catch (UnsupportedJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_UNSUPPORTED);
            return;
        } catch (MalformedJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_MALFORMED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith(ConstRute.AUTH+ConstRute.LOGIN);
    }
}
