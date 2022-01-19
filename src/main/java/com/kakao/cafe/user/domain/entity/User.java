package com.kakao.cafe.user.domain.entity;


import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;

@Getter
@EqualsAndHashCode
public class User {
    @NonNull
    @Size(min = 3, max = 10)
    private final String userId;

    @NonNull
    @Size(min = 8, max = 16)
    private String password;

    private UserInfo userInfo;

    public User(@NonNull String userId, @NonNull String password, UserInfo userInfo) {
        this.userId = userId;
        this.password = password;
        this.userInfo = userInfo;
        validate();
    }

    protected void validate(){
        ValidationService.validate(this);
    }

    public void updateInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public boolean match(String password) {
        return this.password.equals(password);
    }
}
