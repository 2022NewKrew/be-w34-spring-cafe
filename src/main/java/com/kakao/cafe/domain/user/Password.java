package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.IllegalPasswordFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private static final String PASSWORD_FORMAT_EXCEPTION = "비밀번호는 대문자 1개, 소문자 1개, 숫자 1개, 특수문자(@$!%*?&) 1개를 포함하여 최소 8자여야 합니다.";
    private static final String REGEX_PASSWORD_FORMAT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private final String password;

    public Password(String password) {
        validatePasswordFormat(password);
        this.password = password;
    }

    private void validatePasswordFormat(String password) {
        Pattern p = Pattern.compile(REGEX_PASSWORD_FORMAT);
        Matcher m = p.matcher(password);
        if(!m.matches()) {
            throw new IllegalPasswordFormatException(PASSWORD_FORMAT_EXCEPTION);
        }
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
