package com.kakao.cafe.model.user;

import com.kakao.cafe.utility.NullChecker;

import java.util.Objects;

public class UserId {

    private static final int ALLOWED_LENGTH_USERID = 16;

    private final String value;

    public UserId(String userId) {
        checkUserId(userId);

        this.value = userId;
    }

    private void checkUserId(String userId) {
        NullChecker.checkNotNull(userId);

        if (userId.length() > ALLOWED_LENGTH_USERID) {
            throw new IllegalArgumentException(
                    String.format("유저아이디의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_USERID));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
