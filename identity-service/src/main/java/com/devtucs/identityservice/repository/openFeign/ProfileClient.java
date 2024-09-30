package com.devtucs.identityservice.repository.openFeign;

import com.devtucs.identityservice.dto.request.client.ProfileRequest;
import com.devtucs.identityservice.dto.response.client.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${spring.cloud.openfeign.client.config.feignName.url}")
public interface ProfileClient {
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileResponse createProfile(@RequestBody ProfileRequest request);
}
