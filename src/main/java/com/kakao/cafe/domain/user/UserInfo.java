package com.kakao.cafe.domain.user;

import com.kakao.cafe.utils.TimeGenerator;
import lombok.*;

@ToString
@Getter
public class UserInfo {
    private final String userId;
    @Builder.Default
    private String signUpDate = TimeGenerator.todayDate();
    private final String password;
    private final String name;
    private final String email;


    @Builder
    public UserInfo(String userId, String password, String name, String email, String signUpDate) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.signUpDate = signUpDate;
    }


    public boolean hasEqualId(String otherId) {
        return otherId.equals(this.userId);
    }

}
