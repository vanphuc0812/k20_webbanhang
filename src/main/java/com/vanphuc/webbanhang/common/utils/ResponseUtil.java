package com.vanphuc.webbanhang.common.utils;

import com.vanphuc.webbanhang.common.model.ResponeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;

public class ResponseUtil {
    public static ResponseEntity<ResponeDTO> get(Object result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponeDTO.builder()
                        .content(result)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .time(DateTimeUtils.now())
                        .status(status.value())
                        .build(),
                status
        );
    }

    public static ResponseEntity<ResponeDTO> error(Object result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponeDTO.builder()
                        .content(result)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .time(DateTimeUtils.now())
                        .status(status.value())
                        .build(),
                status
        );
    }
}
