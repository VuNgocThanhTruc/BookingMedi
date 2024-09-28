package com.devtucs.identityservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCodeConstant {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategoried error"),
    INVALID_KEY(1001, "Uncategorized error"),
    USER_EXISTED(1002, "User đã tồn tại"),
    INVALID_USERNAME(1003, "Username phải chứa ít nhất 3 ký tự"),
    INVALID_PASSWORD(1004, "Password phải chứa ít nhất 8 ký tự"),
    INVALID_EMAIL(1005, "Trường email không đúng định dạng"),
    INVALID_USERNAME_NOTNULL(1006, "Trường username không được bỏ trống"),
    INVALID_PASSWORD_NOTNULL(1007, "Trường password không được bỏ trống"),
    USER_NOT_FOUND(1008, "User không tồn tại"),
    UNAUTHENTICATED(1009, "User chưa được xác thực"),
    ROLE_NOT_FOUND(1010, "Role không tồn tại"),
    ;

    private int code;
    private String message;
}
