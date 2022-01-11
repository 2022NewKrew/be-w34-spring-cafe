package com.kakao.cafe.user.validator;

import com.google.common.collect.Range;
import com.kakao.cafe.common.Validator;
import com.kakao.cafe.user.exception.InvalidPasswordException;

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
