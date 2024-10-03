package com.devtucs.identityservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthenticationReqInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String authHeader = servletRequestAttributes.getRequest().getHeader("Authorization");

        if (StringUtils.hasText(authHeader))
            requestTemplate.header("Authorization", authHeader);
    }
}
