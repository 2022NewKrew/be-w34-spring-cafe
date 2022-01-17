package com.kakao.cafe.domain.user;

public class Password {
    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public String getValue() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Password)) {
            return false;
        }

        return password.equals(((Password) obj).getValue());
    }

    @Override
    public String toString() {
        return password;
    }
}
