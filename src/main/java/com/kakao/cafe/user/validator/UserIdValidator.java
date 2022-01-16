package com.kakao.cafe.user.validator;

import com.google.common.collect.Range;
import com.kakao.cafe.user.constraint.UserId;
import com.kakao.cafe.user.exception.InvalidUserIdException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserIdValidator implements ConstraintValidator<UserId, String> {
    private static final Range<Integer> RANGE = Range.closed(1, 20);
    private static final Pattern PATTERN = Pattern.compile("^[a-z0-9]+$");

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext context) {
        if (userId == null) {
            throw new InvalidUserIdException(null);
        }
        if (!RANGE.contains(userId.length())) {
            throw new InvalidUserIdException(userId);
        }
        if (!PATTERN.matcher(userId).matches()) {
            throw new InvalidUserIdException(userId);
        }
        return true;
    }
}
