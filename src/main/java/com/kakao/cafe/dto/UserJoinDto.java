package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinDto {
    private String email;
    private String password;
    private String nickName;

    public User toUserWithCurrentDate() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .joinDate(DateUtil.getCurrentCalendar())
                .build();
    }
}
