package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.TimeGenerator;
import lombok.Getter;
import lombok.ToString;
import org.apache.catalina.User;


@ToString
@Getter
public class UserInfo {
    private final String userId;
    private final String signUpDate;
    private final String password;
    private final String name;
    private final String email;

    public UserInfo(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.signUpDate = TimeGenerator.todayDate();
    }


    public boolean hasEqualId(String otherId) {
        return otherId.equals(this.userId);
    }

}
