package com.devtucs.postservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String id;
    String userId;
    String title;
    float price;
    String briefContent;
    String image;
    String content;
    Instant createdAt;
    Instant modifiedAt;
}
