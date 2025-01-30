package com.backendchallenge.userservice.infrastructure.configuration.security.filter;

import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.application.jwt.JwtService;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import com.backendchallenge.userservice.domain.until.JwtConst;
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
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

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
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstExceptions.TOKEN_EMPTY);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/login") || path.startsWith("/api/auth/register");
    }
}
