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
    public boolean equals(Object obj) {
        if(!(obj instanceof Email)) {
            return false;
        }

        return email.equals(((Email) obj).getValue());
    }
}
