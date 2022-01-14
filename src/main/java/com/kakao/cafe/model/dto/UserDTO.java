package com.kakao.cafe.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;
}
