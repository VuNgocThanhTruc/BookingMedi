package com.devtucs.notificationservice.service;

import com.devtucs.notificationservice.dto.request.EmailRequest;
import com.devtucs.notificationservice.dto.response.EmailResponse;
import com.devtucs.notificationservice.exception.AppException;
import com.devtucs.notificationservice.exception.ErrorCodeConstant;
import com.devtucs.notificationservice.repository.client.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;
    @Value("${notification.api-key}")
    @NonFinal
    String apiKey;

    public EmailResponse sendEmail(EmailRequest request) {
        try {
            return emailClient.sendEmail(apiKey, request);
        } catch (FeignException e) {
            throw new AppException(ErrorCodeConstant.BAD_REQUEST);
        }
    }
}
