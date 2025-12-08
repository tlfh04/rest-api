package com.example.restapi.exception;

import com.example.restapi.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // CustomException 처리
    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex){
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode.getCode(),errorCode.getMessage()));
    }
    // Validation 예외 처리
    
    // 그 외 모든 예외 처리
}
