package com.devtucs.apigateway.repository;

import com.devtucs.apigateway.dto.response.IntrospectResp;
import com.devtucs.apigateway.dto.request.IntrospectReq;
import com.devtucs.apigateway.dto.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResp>> introspect(@RequestBody IntrospectReq request);
}
