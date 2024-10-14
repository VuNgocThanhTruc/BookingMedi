package com.devtucs.bookingservice.entity;

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
@Document(value = "booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @MongoId
    String id;
    String userId;
    String postId;
    Instant timeBooking;
    String description;
    Instant createdAt;
    String createdBy;
    Instant modifiedAt;
    String modifiedBy;
}
