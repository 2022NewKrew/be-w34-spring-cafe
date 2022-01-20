package com.kakao.cafe.web;

import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@HandlebarsHelper
public class CustomHandlebarsHelper {
    public String dateTimeToString(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public int listLength(List list) {
        return list.size();
    }
}
