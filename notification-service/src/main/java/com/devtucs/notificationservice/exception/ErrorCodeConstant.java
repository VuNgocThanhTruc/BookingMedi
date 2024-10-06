package com.devtucs.notificationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCodeConstant {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategoried error"),
    INVALID_KEY(1001, "Uncategorized error"),
    INVALID_EMAIL(1002, "Trường email không đúng định dạng"),
    PROFILE_NOT_FOUND(1008, "Profile not found"),
    UNAUTHENTICATED(1009, "User chưa được xác thực"),
    BAD_REQUEST(1010, "Cannot send email"),
    ;

    private int code;
    private String message;
}
