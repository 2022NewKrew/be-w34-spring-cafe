package com.kakao.cafe.model.dto;

import lombok.*;

@Data
public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String email;
}
