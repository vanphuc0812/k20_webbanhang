package com.vanphuc.webbanhang.common.exception;

import com.vanphuc.webbanhang.common.model.ResponeDTO;
import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WBHBussinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponeDTO> handleBusinessException(WBHBussinessException exception) {
        return ResponseUtil.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<ResponeDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseUtil.error(exception, HttpStatus.BAD_REQUEST);
    }
}
