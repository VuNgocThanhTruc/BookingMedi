package com.devtucs.identityservice.controller;

import com.devtucs.identityservice.dto.request.IntrospectReq;
import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.response.ApiResponse;
import com.devtucs.identityservice.dto.response.AuthenticationResponse;
import com.devtucs.identityservice.dto.response.IntrospectResp;
import com.devtucs.identityservice.entity.User;
import com.devtucs.identityservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody UserCreationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService
                .createAuthenticationResponse(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(authenticationResponse.isAuthenticated())
                        .token(authenticationResponse.getToken())
                        .build())
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResp> introspect(@RequestBody IntrospectReq request) throws ParseException, JOSEException {

        log.info(request.getToken());

        return ApiResponse.<IntrospectResp>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
