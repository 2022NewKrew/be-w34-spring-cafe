package com.kakao.cafe.domain.user;

import com.kakao.cafe.utils.TimeGenerator;
import lombok.*;

@ToString
@Getter
@Builder
public class UserInfo {
    private final String userId;
    @Builder.Default
    private String signUpDate = TimeGenerator.todayDate();
    private final String password;
    private final String name;
    private final String email;


    public boolean hasEqualId(String otherId) {
        return otherId.equals(this.userId);
    }

}
