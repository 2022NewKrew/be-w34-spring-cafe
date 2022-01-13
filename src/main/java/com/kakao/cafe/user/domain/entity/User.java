package com.kakao.cafe.user.domain.entity;


import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
public class User {
    @Size(min = 3, max = 10)
    private final String userId;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 3, max = 5)
    private String name;

    @Email
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = Objects.requireNonNull(userId);
        this.password = Objects.requireNonNull(password);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);

        ValidationService.validate(this);
    }
}
