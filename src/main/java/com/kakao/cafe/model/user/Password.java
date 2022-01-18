package com.kakao.cafe.model.user;

import com.kakao.cafe.utility.NullChecker;
import java.util.Objects;

public class Password {

    private static final int ALLOWED_LENGTH_PASSWORD = 16;

    private final String value;

    public Password(String password) {
        checkPassword(password);

        this.value = password.trim();
    }

    private void checkPassword(String password) {
        NullChecker.checkNotNull(password);

        if (password.length() > ALLOWED_LENGTH_PASSWORD) {
            throw new IllegalArgumentException(
                    String.format("패스워드의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_PASSWORD));
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
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
