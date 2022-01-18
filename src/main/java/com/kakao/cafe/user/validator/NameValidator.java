package com.kakao.cafe.user.validator;

import com.google.common.collect.Range;
import com.kakao.cafe.user.constraint.Name;
import com.kakao.cafe.user.exception.InvalidNameException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
    private static final Range<Integer> RANGE = Range.closed(1, 20);

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            throw new InvalidNameException(null);
        }
        if (!RANGE.contains(name.length())) {
            throw new InvalidNameException(name);
        }
        return true;
    }
}
