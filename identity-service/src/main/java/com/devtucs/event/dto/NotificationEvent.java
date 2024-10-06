package com.devtucs.event.dto;

import com.devtucs.identityservice.dto.request.MailUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEvent {
    String channel;
    List<MailUser> recipients;
    String templateCode;
    Map<String, Object> paramsTemplate;
    String subject;
    String body;
}
