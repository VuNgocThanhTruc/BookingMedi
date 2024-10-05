package org.devtucs.notificationservice.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequest {
    MailUser sender;
    List<MailUser> to;
    String htmlContent;
    String subject;
}
