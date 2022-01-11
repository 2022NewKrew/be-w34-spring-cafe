package com.kakao.cafe.user.validator;

import com.kakao.cafe.common.Validator;
import com.kakao.cafe.user.exception.InvalidEmailException;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void validate(String email) {
        if (email == null) {
            throw new InvalidEmailException(null);
        }
        if (!PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }
}
