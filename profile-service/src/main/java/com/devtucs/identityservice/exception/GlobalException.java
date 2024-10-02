package com.devtucs.identityservice.exception;

import com.devtucs.identityservice.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(ErrorCodeConstant.UNCATEGORIZED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCodeConstant.UNCATEGORIZED_EXCEPTION.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCodeConstant errorCodeConstant = exception.getErrorCodeConstant();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCodeConstant.getCode());
        apiResponse.setMessage(errorCodeConstant.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidationException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCodeConstant errorCodeConstant = ErrorCodeConstant.INVALID_KEY;

        try {
            errorCodeConstant = ErrorCodeConstant.valueOf(enumKey);
        } catch (IllegalArgumentException e){

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCodeConstant.getCode());
        apiResponse.setMessage(errorCodeConstant.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
