package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WriteTime {
    private Date writeTime;

    public WriteTime() {
        writeTime = new Date();
    }
}
