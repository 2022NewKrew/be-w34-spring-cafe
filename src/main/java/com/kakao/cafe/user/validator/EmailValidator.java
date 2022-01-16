package com.kakao.cafe.user.validator;

import com.kakao.cafe.user.constraint.Email;
import com.kakao.cafe.user.exception.InvalidEmailException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            throw new InvalidEmailException(null);
        }
        if (!PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
        return true;
    }
}
