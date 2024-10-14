package com.devtucs.identityservice.service;

import com.devtucs.event.dto.NotificationEvent;
import com.devtucs.identityservice.dto.request.MailUser;
import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.request.UserRolesUpdateRequest;
import com.devtucs.identityservice.dto.request.UserUpdateRequest;
import com.devtucs.identityservice.dto.request.client.ProfileRequest;
import com.devtucs.identityservice.dto.response.UserResponse;
import com.devtucs.identityservice.entity.User;
import com.devtucs.identityservice.entity.Role;
import com.devtucs.identityservice.exception.AppException;
import com.devtucs.identityservice.exception.ErrorCodeConstant;
import com.devtucs.identityservice.mapper.UserMapper;
import com.devtucs.identityservice.mapper.openFeign.ProfileMapper;
import com.devtucs.identityservice.repository.RoleRepository;
import com.devtucs.identityservice.repository.UserRepository;
import com.devtucs.identityservice.repository.openFeign.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    ProfileClient profileClient;
    ProfileMapper profileMapper;
    KafkaTemplate<String, Object> kafkaTemplate; //k,v

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCodeConstant.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = new Role();
        role.setName(com.devtucs.identityservice.enums.Role.USER.name());

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        user = userRepository.save(user);

        ProfileRequest profileRequest = profileMapper.toProfileCreationReq(request);
        profileRequest.setUserId(user.getId());

        profileClient.createProfile(profileRequest);

//      registration successfully send message with kafka
        List<MailUser> recipients = new ArrayList<>();
        recipients.add(MailUser.builder()
                .email(request.getEmail())
                .name(request.getUsername())
                .build());

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .channel("EMAIL")
                .recipients(recipients)
                .subject("ACCEPT REGISTRATION WITH BOOKINGMEDI")
                .body("<h2>Welcome to BookingMedi</h2>" +
                        "<p>Dear " + request.getUsername() + ",</p>" +
                        "<p></p>" +
                        "<p>Thank you for completing your registration with BookingMedi.</p>" +
                        "<p></p>" +
                        "<p>This email serves as a confirmation that your account is activated and that you are officially a part of the BookingMedi. Enjoy!</p>" +
                        "<p></p>" +
                        "<p>If you have any questions or suggestions, please don't hesitate to reach out at bookingmedi@gmail.com to see how we can meet your needs. We'd love to hear your feedback!</p>" +
                        "<p></p>" +
                        "<p>Best,</p>" +
                        "<p></p>" +
                        "<p>BookingMedi.</p>")

                .build();

        kafkaTemplate.send("notification-delivery", notificationEvent);

        return userMapper.toUserResponse(user);
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
