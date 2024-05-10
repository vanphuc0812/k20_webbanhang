package com.vanphuc.webbanhang.common.utils;

import com.vanphuc.webbanhang.common.model.ResponeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static ResponseEntity<ResponeDTO> error(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponeDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(List.of(exception.getMessage()))
                        .time(DateTimeUtils.now())
                        .status(status.value())
                        .build(),
                status
        );
    }

    public static ResponseEntity<ResponeDTO> error(MethodArgumentNotValidException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponeDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(
                                Arrays.stream(exception.getDetailMessageArguments())
                                        .map(Object::toString)
                                        .filter(object -> !"".equals(object))
                                        .toList()
                        )
                        .time(DateTimeUtils.now())
                        .status(status.value())
                        .build(),
                status
        );
    }

}
