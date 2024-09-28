package com.devtucs.profileservice.mapper;

import com.devtucs.profileservice.dto.request.ProfileRequest;
import com.devtucs.profileservice.dto.response.ProfileResponse;
import com.devtucs.profileservice.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileRequest request);

    ProfileResponse toProfileResponse(Profile profile);

    void toUpdateProfile(@MappingTarget Profile profile, ProfileRequest request);
}
