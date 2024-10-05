package org.devtucs.notificationservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{
    ErrorCodeConstant errorCodeConstant;

    public AppException(ErrorCodeConstant errorCodeConstant){
        super(errorCodeConstant.getMessage());
        this.errorCodeConstant = errorCodeConstant;
    }
}
