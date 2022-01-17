package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString(exclude = "password")
@Builder
@Getter
public class User {

    @Nullable
    private final Long id;

    @NotBlank
    private final String uid;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+\\w{2,3}")
    private final String email;

    @Nullable
    private final LocalDateTime createdAt;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
