package com.devtucs.identityservice.dto.response.client;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    String userId;
    String fullName;
    LocalDate dob;
    String email;
    int gender;
    String address;
}
