package com.devtucs.identityservice.mapper.openFeign;

import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.request.client.ProfileRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileRequest toProfileCreationReq(UserCreationRequest request);
}
