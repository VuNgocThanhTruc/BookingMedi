package com.devtucs.postservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(value = "post")
public class Post {
    @MongoId
    String id;
    String userId;
    String title;
    float price;
//    Specialty specialty;
    String briefContent;
    String image;
    String content;
    Instant createdAt;
    Instant modifiedAt;
}
