package com.kakao.cafe.testutil.user;

import com.kakao.cafe.dto.user.ProfileUpdateDto;
import com.kakao.cafe.dto.user.UserJoinDto;

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

    public static ProfileUpdateDto createProfileUpdateDto() {
        return createProfileUpdateDto(Long.valueOf(1), "gallix");
    }

    public static ProfileUpdateDto createProfileUpdateDto(String nickName) {
        return createProfileUpdateDto(Long.valueOf(1), nickName);
    }

    public static ProfileUpdateDto createProfileUpdateDto(Long id) {
        return createProfileUpdateDto(id, "gallix");
    }

    public static ProfileUpdateDto createProfileUpdateDto(Long id, String nickName) {
        return ProfileUpdateDto.builder()
                .id(id)
                .nickName(nickName)
                .password("abcd1234!")
                .build();
    }
}
