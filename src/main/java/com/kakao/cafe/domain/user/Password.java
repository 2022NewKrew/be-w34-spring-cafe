package com.kakao.cafe.domain.user;

import java.util.Objects;

public class Password {

    private final String password;

    public Password(String password) {
        if (password == null || password.trim().length() == 0)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        this.password = password.trim();
    }

    public boolean is(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                '}';
    }
}
