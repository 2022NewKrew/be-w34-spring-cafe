package com.kakao.cafe.user.validator;

import com.google.common.collect.Range;
import com.kakao.cafe.common.Validator;
import com.kakao.cafe.user.exception.InvalidUserIdException;

import java.util.regex.Pattern;

public class UserIdValidator implements Validator<String> {
    private static final Range<Integer> RANGE = Range.closed(1, 20);
    private static final Pattern PATTERN = Pattern.compile("^[a-z0-9]+$");

    @Override
    public void validate(String userId) {
        if (userId == null) {
            throw new InvalidUserIdException(null);
        }
        if (!RANGE.contains(userId.length())) {
            throw new InvalidUserIdException(userId);
        }
        if (!PATTERN.matcher(userId).matches()) {
            throw new InvalidUserIdException(userId);
        }
    }
}
