package com.kakao.cafe.testutil.user;

import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.UserJoinDto;
import org.springframework.context.annotation.Profile;

public final class UserDtoUtil {
    private UserDtoUtil() {
    }

    public static UserJoinDto createUserJoinDto() {
        return createUserJoinDto("gallix@kakao.com", "gallix");
    }

    public static UserJoinDto createUserJoinDto(String email, String nickName) {
        return UserJoinDto.builder()
                .email(email)
                .nickName(nickName)
                .password("abcd1234!")
                .build();
    }

    public static ProfileDto createProfileDto() {
        return createProfileDto(Long.valueOf(1), "gallix");
    }

    public static ProfileDto createProfileDto(String nickName) {
        return createProfileDto(Long.valueOf(1), nickName);
    }

    public static ProfileDto createProfileDto(Long id) {
        return createProfileDto(id, "gallix");
    }

    public static ProfileDto createProfileDto(Long id, String nickName) {
        return ProfileDto.builder()
                .id(id)
                .nickName(nickName)
                .password("abcd1234!")
                .build();
    }
}
