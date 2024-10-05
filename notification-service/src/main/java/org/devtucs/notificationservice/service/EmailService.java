package org.devtucs.notificationservice.service;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.devtucs.notificationservice.dto.request.EmailRequest;
import org.devtucs.notificationservice.dto.response.EmailResponse;
import org.devtucs.notificationservice.exception.AppException;
import org.devtucs.notificationservice.exception.ErrorCodeConstant;
import org.devtucs.notificationservice.repository.client.EmailClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;
    @Value("${spring.brevo.api-key}")
    @NonFinal
    String apiKey;

    public EmailResponse sendEmail(@RequestBody EmailRequest request) {
        try {
            return emailClient.sendEmail(apiKey, request);
        } catch (FeignException e) {
            throw new AppException(ErrorCodeConstant.BAD_REQUEST);
        }
    }
}
