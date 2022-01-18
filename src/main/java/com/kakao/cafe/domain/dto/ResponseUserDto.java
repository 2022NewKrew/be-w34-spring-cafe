package com.kakao.cafe.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ResponseUserDto {
    private long id;
    private String userId;
    private String name;
    private String email;
    private Date joinedAt;

    public String getJoinedAt(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(joinedAt);
    }
}
