package com.kakao.cafe.validator.user;

import com.google.common.collect.Range;
import com.kakao.cafe.exception.user.InvalidPasswordException;
import com.kakao.cafe.validator.Validator;

public class PasswordValidator implements Validator<String> {
    private static final Range<Integer> RANGE = Range.closed(8, 32);

    @Override
    public void validate(String password) {
        if (password == null) {
            throw new InvalidPasswordException(null);
        }
        if (!RANGE.contains(password.length())) {
            throw new InvalidPasswordException(password);
        }
    }
}
