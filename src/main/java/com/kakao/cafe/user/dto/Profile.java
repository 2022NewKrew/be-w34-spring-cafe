package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class Profile {

    private Long userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;

    public static Profile of(User user) {
        return Profile.builder()
            .userId(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .createdAt(user.getCreatedAt())
            .build();
    }
}
