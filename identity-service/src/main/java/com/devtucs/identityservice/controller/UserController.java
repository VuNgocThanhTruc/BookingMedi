package com.devtucs.identityservice.controller;

import com.devtucs.identityservice.dto.request.UserRolesUpdateRequest;
import com.devtucs.identityservice.dto.response.ApiResponse;
import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.request.UserUpdateRequest;
import com.devtucs.identityservice.dto.response.UserResponse;
import com.devtucs.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);

        log.info("Username: {}", authentication.getName());
        log.info("getAuthorities: {}", authentication.getAuthorities());

        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/profile")
    private ApiResponse<UserResponse> getUserProFile() {
        UserResponse userResponse = userService.getUserProfile();

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @GetMapping("/{userId}")
    private ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("/{userId}")
    private ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @PutMapping("/updateUserRoles")
    private ApiResponse<UserResponse> updateUserRoles(@RequestBody UserRolesUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserRoles(request))
                .build();
    }

    @DeleteMapping("/{userId}")
    private ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
