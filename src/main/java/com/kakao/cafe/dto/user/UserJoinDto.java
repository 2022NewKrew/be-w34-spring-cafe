package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJoinDto {
    private final String email;
    private String password;
    private final String nickName;

    public User toUserWithCurrentDate() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .joinDate(DateUtil.getCurrentCalendar())
                .build();
    }
}
