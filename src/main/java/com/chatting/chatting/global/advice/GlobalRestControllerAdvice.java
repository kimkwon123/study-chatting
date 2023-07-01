package com.chatting.chatting.global.advice;

import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.response.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalRestControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(CustomException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ResponseJson.error(e.getErrorCode().name(),e.getErrorCode().getMessage()));
    }



}
