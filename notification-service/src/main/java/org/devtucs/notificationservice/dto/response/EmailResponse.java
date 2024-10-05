package org.devtucs.notificationservice.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.devtucs.notificationservice.dto.request.MailUser;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailResponse {
    String messageId;
}
