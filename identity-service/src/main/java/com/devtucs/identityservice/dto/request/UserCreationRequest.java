package com.devtucs.identityservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    @NotNull(message = "INVALID_USERNAME_NOTNULL")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    @NotNull(message = "INVALID_PASSWORD_NOTNULL")
    String password;

    String fullName;
    LocalDate dob;
    @Email(message = "INVALID_EMAIL")
    String email;
    int gender;
    String address;
}
