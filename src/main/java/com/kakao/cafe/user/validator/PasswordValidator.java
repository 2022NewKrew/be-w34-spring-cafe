package com.kakao.cafe.user.validator;

import com.google.common.collect.Range;
import com.kakao.cafe.user.constraint.Password;
import com.kakao.cafe.user.exception.InvalidPasswordException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private static final Range<Integer> RANGE = Range.closed(8, 32);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            throw new InvalidPasswordException(null);
        }
        if (!RANGE.contains(password.length())) {
            throw new InvalidPasswordException(password);
        }
        return true;
    }
}
