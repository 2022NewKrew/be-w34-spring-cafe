package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.base.BaseEntity;
import com.kakao.cafe.domain.user.exception.PasswordNotMatchException;
import com.kakao.cafe.domain.user.exception.UserNotMathException;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private String email;
    private String password;
    private String nickname;

    @Builder
    public User(Long id, String email, String password, String nickname, LocalDateTime createdAt, Boolean isDeleted) {
        super(id, createdAt, isDeleted);
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateInfo(User user) {
        this.nickname = user.getNickname();
    }

    public void validateId(Long id) {
        if (!getId().equals(id)) throw new UserNotMathException();
    }

    public void validatePassword(String password) {
        if (!getPassword().equals(password)) throw new PasswordNotMatchException();
    }
}
