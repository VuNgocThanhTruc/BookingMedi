package com.devtucs.identityservice.mapper;

import com.devtucs.identityservice.dto.request.ProfileRequest;
import com.devtucs.identityservice.dto.response.ProfileResponse;
import com.devtucs.identityservice.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileRequest request);

    ProfileResponse toProfileResponse(Profile profile);

    void toUpdateProfile(@MappingTarget Profile profile, ProfileRequest request);
}
