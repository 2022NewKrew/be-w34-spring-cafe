package com.kakao.cafe.domain.user;

import com.kakao.cafe.utils.TimeGenerator;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import lombok.*;


@ToString
public class UserInfo {
    private final String userId;
    private final String signUpDate;
    private final String password;
    private final String name;
    private final String email;

    @Builder
    public UserInfo(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.signUpDate = TimeGenerator.todayDate();
    }

    public String getUserId() {
        return userId;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static UserInfoBuilder builder(UserCreateRequestDto userCreateRequestDto) {
        return new UserInfoBuilder()
                .userId(userCreateRequestDto.getUserId())
                .name(userCreateRequestDto.getName())
                .email(userCreateRequestDto.getEmail())
                .password(userCreateRequestDto.getPassword());
    }


    public boolean hasEqualId(String otherId) {
        return otherId.equals(this.userId);
    }

}
