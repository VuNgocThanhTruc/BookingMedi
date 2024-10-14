package com.devtucs.notificationservice.controller;

import com.devtucs.notificationservice.dto.request.EmailRequest;
import com.devtucs.notificationservice.dto.request.MailUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import com.devtucs.event.dto.NotificationEvent;
import com.devtucs.notificationservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    EmailService emailService;
//
    @KafkaListener(topics = "notification-delivery")
    public void listenKafka(NotificationEvent event) {
        log.info("Message received: {}", event);

        emailService.sendEmail(EmailRequest.builder()
                .sender(MailUser.builder()
                        .name("BookingMedi")
                        .email("vutruc0702@gmail.com")
                        .build())
                .subject(event.getSubject())
                .to(event.getRecipients())
                .htmlContent(event.getBody())
                .build());
    }
}
