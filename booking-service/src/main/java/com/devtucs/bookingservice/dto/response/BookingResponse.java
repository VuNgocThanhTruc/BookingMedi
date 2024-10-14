package com.devtucs.bookingservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
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
