package org.devtucs.apigateway.repository;

import org.devtucs.apigateway.dto.request.IntrospectReq;
import org.devtucs.apigateway.dto.response.ApiResponse;
import org.devtucs.apigateway.dto.response.IntrospectResp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResp>> introspect(@RequestBody IntrospectReq request);
}
