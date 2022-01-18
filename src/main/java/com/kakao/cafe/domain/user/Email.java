package com.kakao.cafe.domain.user;

public class Email {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    public String getValue() {
        return email;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Email)) {
            return false;
        }

        Email other = (Email) obj;
        return email.equals(other.getValue());
    }

    @Override
    public String toString() {
        return email;
    }
}
