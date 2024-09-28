package com.devtucs.identityservice.service;

import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.request.UserRolesUpdateRequest;
import com.devtucs.identityservice.dto.request.UserUpdateRequest;
import com.devtucs.identityservice.dto.response.UserResponse;
import com.devtucs.identityservice.entity.User;
import com.devtucs.identityservice.entity.Role;
import com.devtucs.identityservice.exception.AppException;
import com.devtucs.identityservice.exception.ErrorCodeConstant;
import com.devtucs.identityservice.mapper.UserMapper;
import com.devtucs.identityservice.repository.RoleRepository;
import com.devtucs.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCodeConstant.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role  = new Role();
        role.setName(com.devtucs.identityservice.enums.Role.USER.name());

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.USER_NOT_FOUND));

        userMapper.toUpdateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasAuthority('GET_USERS')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.USER_NOT_FOUND)));
    }

    public UserResponse getUserProfile() {
        String nameContextHolder = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(nameContextHolder)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUserRoles(UserRolesUpdateRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCodeConstant.USER_NOT_FOUND));

        Set<Role> roles = request.getNameRoles().stream()
                .map(role -> roleRepository.findById(role)
                        .orElseThrow(() -> new AppException(ErrorCodeConstant.ROLE_NOT_FOUND)))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

}
