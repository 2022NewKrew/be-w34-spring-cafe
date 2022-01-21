package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private String password;

    public boolean chcekPassword(String password) {
        return this.password.equals(password);
    }

    public void updateEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.email, this.password, this.userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User)obj;

        return Objects.equals(name, user.getName()) &&
                Objects.equals(userId, user.getUserId()) &&
                Objects.equals(email, user.getEmail()) &&
                Objects.equals(password, user.getPassword());
    }
}
