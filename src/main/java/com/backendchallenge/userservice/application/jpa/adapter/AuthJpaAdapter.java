package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IAuthPersistencePort;
import com.backendchallenge.userservice.domain.until.JwtConst;
import com.backendchallenge.userservice.application.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUserRepository userRepository;

    @Override
    public User authenticate(String email, String password) {
        Authentication authUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        UserEntity userEntity = (UserEntity) authUser.getPrincipal();
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole().getName());


        return user;
    }

    @Override
    public String generateToken(User user) {
        if (user == null) {
            return null;
        }
        return jwtService.generateToken(user, generateExtraClaims(user));
    }

    @Override
    public boolean validateCredentials(String userEmail, String userPassword) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userEmail,
                            userPassword
                    )
            );
            return true;
        } catch (BadCredentialsException e) {
            return false;
        }
    }
    private Map<String,Object> generateExtraClaims(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail());
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID,userEntity.getId());
        extraClaims.put(JwtConst.ROLE,userEntity.getRole().getName());
        return extraClaims;
    }

}
