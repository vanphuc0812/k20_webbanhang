package com.vanphuc.webbanhang.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public final static String DATETIME_FORMAT = "dd-MM-yyyy hh:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
 }
