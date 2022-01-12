package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import lombok.Getter;

@Getter
public class UserId {
    private static final String format = "^[a-z0-9_-]{5,20}";

    private final String userId;

    public UserId(String userId) {
        validateNull(userId);
        validateFormat(userId);
        this.userId = userId;
    }

    private void validateNull(String userId) {
        if (userId == null) {
            throw new UserException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateFormat(String userId) {
        if (!userId.matches(format)) {
            throw new UserException(ErrorCode.INVALID_USER_ID);
        }
    }

    public String getUserId() {
        return userId;
    }
}
