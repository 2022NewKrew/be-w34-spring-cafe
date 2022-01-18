package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserShowDto {
    private Integer id;
    private String userId;
    private String password;
    private String userName;
    private String email;
}
