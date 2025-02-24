package com.backendchallenge.userservice.application.jpa.mapper;

import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper (componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(User user);
}
