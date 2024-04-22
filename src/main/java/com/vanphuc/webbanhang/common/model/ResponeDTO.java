package com.vanphuc.webbanhang.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponeDTO {
    private Object content;
    private boolean hasErrors;
    private List<String> errors;
    private String time;
    private int status;
}
