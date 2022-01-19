package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString(exclude = "password")
@Getter
public class User {

    private final Long id;

    private final String uid;
    private final String password;
    private final String name;
    private final String email;

    private final LocalDateTime createdAt;

    @Builder
    public User(@Nullable Long id, @NotBlank String uid, @NotBlank String password,
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+\\w{2,3}") String email,
        @NotNull LocalDateTime createdAt) {
        this.id = id;
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
