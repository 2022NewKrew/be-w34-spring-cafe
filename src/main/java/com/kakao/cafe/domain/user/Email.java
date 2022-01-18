package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.IllegalEmailFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private static final String EMAIL_FORMAT_EXCEPTION = "이메일 형식이 맞지 않습니다.";
    private static final String REGEX_EMAIL_FORMAT = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    private final String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) throws IllegalEmailFormatException {
        Pattern p = Pattern.compile(REGEX_EMAIL_FORMAT);
        Matcher m = p.matcher(email);
        if(!m.matches()) {
            throw new IllegalEmailFormatException(EMAIL_FORMAT_EXCEPTION);
        }
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
