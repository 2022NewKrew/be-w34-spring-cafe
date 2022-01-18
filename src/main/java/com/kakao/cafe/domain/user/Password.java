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
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Password)) {
            return false;
        }

        Password other = (Password) obj;
        return password.equals(other.getValue());
    }

    @Override
    public String toString() {
        return password;
    }
}
