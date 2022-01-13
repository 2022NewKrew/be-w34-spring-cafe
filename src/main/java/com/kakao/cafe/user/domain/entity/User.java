package com.kakao.cafe.user.domain.entity;


import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class User {
    @NonNull
    @Size(min = 3, max = 10)
    private final String userId;

    @NonNull
    @Size(min = 8, max = 16)
    private String password;

    @NonNull
    @Size(min = 3, max = 5)
    private String name;

    @NonNull
    @Email
    private String email;

    @PostConstruct
    protected void validate(){
        ValidationService.validate(this);
    }
}
