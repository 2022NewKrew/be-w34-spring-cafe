package com.kakao.cafe.user.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class Profile {

    private Long userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
}
