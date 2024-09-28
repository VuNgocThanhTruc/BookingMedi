package com.devtucs.identityservice.mapper;

import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.request.UserUpdateRequest;
import com.devtucs.identityservice.dto.response.UserResponse;
import com.devtucs.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void toUpdateUser(@MappingTarget User user, UserUpdateRequest request);
}
