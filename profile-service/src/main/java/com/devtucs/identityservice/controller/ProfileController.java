package com.devtucs.identityservice.controller;

import com.devtucs.identityservice.dto.request.ProfileRequest;
import com.devtucs.identityservice.dto.response.ApiResponse;
import com.devtucs.identityservice.dto.response.ProfileResponse;
import com.devtucs.identityservice.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    ProfileService profileService;

    @GetMapping("/{userId}")
    public ApiResponse<ProfileResponse> getProfile(@PathVariable String userId){
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getProfile(userId))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<ProfileResponse> create(@RequestBody ProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.create(request))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<ProfileResponse> update(@PathVariable String userId, @RequestBody ProfileRequest request){

        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.update(userId, request))
                .build();
    }

    @DeleteMapping
    public ApiResponse<String> delete(@PathVariable String userId){
        profileService.delete(userId);
        return ApiResponse.<String>builder()
                .result("Delete successfully!")
                .build();
    }
}
