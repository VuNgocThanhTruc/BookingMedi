package org.devtucs.apigateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.devtucs.apigateway.dto.request.IntrospectReq;
import org.devtucs.apigateway.dto.response.ApiResponse;
import org.devtucs.apigateway.dto.response.IntrospectResp;
import org.devtucs.apigateway.repository.IdentityClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResp>> introspect(@RequestBody String request){
        return identityClient.introspect(IntrospectReq.builder()
                .token(request)
                .build());
    }

}
