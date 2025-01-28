package com.backendchallenge.userservice.infrastructure.configuration;

import com.backendchallenge.userservice.application.jpa.adapter.EncoderJpaAdapter;
import com.backendchallenge.userservice.application.jpa.adapter.RoleJpaAdapter;
import com.backendchallenge.userservice.application.jpa.adapter.UserJpaAdapter;
import com.backendchallenge.userservice.application.jpa.mapper.IRoleEntityMapper;
import com.backendchallenge.userservice.application.jpa.mapper.IUserEntityMapper;
import com.backendchallenge.userservice.application.jpa.repository.IRoleRepository;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.domain.api.IRoleServicePort;
import com.backendchallenge.userservice.domain.api.IUserServicePort;
import com.backendchallenge.userservice.domain.spi.IEncoderPersistencePort;
import com.backendchallenge.userservice.domain.spi.IRolePersistencePort;
import com.backendchallenge.userservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.userservice.domain.usecase.RoleCase;
import com.backendchallenge.userservice.domain.usecase.UserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, roleEntityMapper, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IEncoderPersistencePort encoderPersistencePort(){
        return new EncoderJpaAdapter(passwordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserCase(roleServicePort(),userPersistencePort(), encoderPersistencePort());
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleCase(rolePersistencePort());
    }
}
