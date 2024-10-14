package com.devtucs.bookingservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCodeConstant {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategoried error"),
    INVALID_KEY(1001, "Uncategorized error"),
    BOOKING_NOT_FOUND(1008, "Booking not found"),
    UNAUTHENTICATED(1009, "User chưa được xác thực"),
    ;

    private int code;
    private String message;
}
