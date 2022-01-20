package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SessionUser {
    private long id;
    private String userId;
    private String name;
}
