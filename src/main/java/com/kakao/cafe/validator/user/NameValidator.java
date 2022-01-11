package com.kakao.cafe.validator.user;

import com.google.common.collect.Range;
import com.kakao.cafe.exception.user.InvalidNameException;
import com.kakao.cafe.validator.Validator;

public class NameValidator implements Validator<String> {
    private static final Range<Integer> RANGE = Range.closed(1, 20);

    @Override
    public void validate(String name) {
        if (name == null) {
            throw new InvalidNameException(null);
        }
        if (!RANGE.contains(name.length())) {
            throw new InvalidNameException(name);
        }

    }
}
