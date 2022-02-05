package com.kakao.cafe.domain;

import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UserId userId = (UserId) object;
        return Objects.equals(this.userId, userId.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
