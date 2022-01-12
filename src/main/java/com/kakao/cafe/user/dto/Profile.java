package com.kakao.cafe.user.dto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public class Profile {

    private Long userId;
    private String email;
    private String username;
    private LocalDate createdAt;
}
