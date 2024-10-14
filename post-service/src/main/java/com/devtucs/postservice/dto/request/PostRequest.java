package com.devtucs.postservice.dto.request;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {
    String userId;
    String title;
    float price;
    String briefContent;
    String image;
    String content;
    Instant createdAt;
    Instant modifiedAt;
}
