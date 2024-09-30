package com.devtucs.profileservice.service;

import com.devtucs.profileservice.dto.request.ProfileRequest;
import com.devtucs.profileservice.dto.response.ProfileResponse;
import com.devtucs.profileservice.entity.Profile;
import com.devtucs.profileservice.exception.AppException;
import com.devtucs.profileservice.exception.ErrorCodeConstant;
import com.devtucs.profileservice.mapper.ProfileMapper;
import com.devtucs.profileservice.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    public ProfileResponse getProfile(String request) {
        Profile profile = profileRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.PROFILE_NOT_FOUND));

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse create(ProfileRequest request) {
        Profile profile = profileMapper.toProfile(request);

        profile = profileRepository.save(profile);

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse update(String id, ProfileRequest request) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.PROFILE_NOT_FOUND));
        profileMapper.toUpdateProfile(profile, request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    public void delete(String request) {
        Profile profile = profileRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.PROFILE_NOT_FOUND));
        profileRepository.delete(profile);
    }
}
