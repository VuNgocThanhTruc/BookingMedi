package com.devtucs.bookingservice.dto.request;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    String userId;
    String postId;
    Instant timeBooking;
    String description;
    Instant createdAt;
    String createdBy;
    Instant modifiedAt;
    String modifiedBy;
}
