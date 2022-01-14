package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String email;
    private String username;
    private String password;
    private LocalDateTime regDate, modDate;

}
