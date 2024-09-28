package com.devtucs.identityservice.service;

import com.devtucs.identityservice.dto.request.IntrospectReq;
import com.devtucs.identityservice.dto.request.UserCreationRequest;
import com.devtucs.identityservice.dto.response.AuthenticationResponse;
import com.devtucs.identityservice.dto.response.IntrospectResp;
import com.devtucs.identityservice.entity.User;
import com.devtucs.identityservice.exception.AppException;
import com.devtucs.identityservice.exception.ErrorCodeConstant;
import com.devtucs.identityservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${spring.security.oauth2.resourceserver.jwt.private-key}")
    private String PRIVATE_KEY;

    private boolean authenticated(UserCreationRequest request, User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        passwordEncoder.encode(request.getPassword());

        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(user.getUsername()).issuer("devtucs").issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())).claim("roles", buildRole(user)).build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(PRIVATE_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResponse createAuthenticationResponse(UserCreationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCodeConstant.USER_NOT_FOUND));

        if (!authenticated(request, user)) throw new AppException(ErrorCodeConstant.UNAUTHENTICATED);

        return AuthenticationResponse.builder().authenticated(authenticated(request, user)).token(generateToken(user)).build();
    }

    public IntrospectResp introspect(IntrospectReq request) throws JOSEException, ParseException {
        String token = request.getToken();

        JWSVerifier verifier = new MACVerifier(PRIVATE_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expireDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        return IntrospectResp.builder().valid(verified && expireDate.after(new Date())).build();

    }

    private String buildRole(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) user.getRoles().forEach(
                role -> { stringJoiner.add("ROLE_"+role.getName());
            if (!CollectionUtils.isEmpty(role.getPermissions()))
                role.getPermissions().forEach(
                        permission -> stringJoiner.add(permission.getName()));
        });

        return stringJoiner.toString();
    }
}
