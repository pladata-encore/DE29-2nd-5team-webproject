package com.example.travelproject.config.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public String localtimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }
}
