package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserListElement {
    private String userId;
    private String name;
    private String email;
}
