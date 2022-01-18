package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.IllegalNameFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
    private static final String NANE_FORMAT_EXCEPTION = "이름은 최소 2자 이상 한글만 입력해주세요.";
    private static final String REGEX_NAME_FORMAT = "^[가-힣]{2,}$";

    private final String name;

    public Name(String name) {
        validateNameFormat(name);
        this.name = name;
    }

    private void validateNameFormat(String name) throws IllegalNameFormatException{
        Pattern p = Pattern.compile(REGEX_NAME_FORMAT);
        Matcher m = p.matcher(name);
        if(!m.matches()) {
            throw new IllegalNameFormatException(NANE_FORMAT_EXCEPTION);
        }
    }


    public String getValue() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Name)) {
            return false;
        }

        Name other = (Name) obj;
        return name.equals(other.getValue());
    }

    @Override
    public String toString() {
        return name;
    }
}
