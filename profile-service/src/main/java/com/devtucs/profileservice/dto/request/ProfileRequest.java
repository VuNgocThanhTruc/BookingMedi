package com.devtucs.profileservice.dto.request;


import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequest {
    String fullName;
    LocalDate dob;
    @Email(message = "INVALID_EMAIL")
    String email;
    int gender;
    String address;
}
