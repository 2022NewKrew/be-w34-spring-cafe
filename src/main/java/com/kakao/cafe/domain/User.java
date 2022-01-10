package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;
import com.kakao.cafe.util.SecurePassword;

import java.util.Objects;

public class User {
    public static final String ID_REGEX = "[0-9a-z]+";
    public static final int ID_MIN = 6;
    public static final int ID_MAX = 12;
    public static final String  PW_REGEX = "[0-9a-zA-Z]+";
    public static final int PW_MIN = 8;
    public static final int PW_MAX = 18;
    public static final String NAME_REGEX = ".+";
    public static final int NAME_MIN = 1;
    public static final int NAME_MAX = 32;
    public static final String EMAIL_REGEX = "[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}";
    public static final int EMAIL_MIN = 7;
    public static final int EMAIL_MAX = 127;

    private static long auto_increment = 1;
    private final long idx;
    private final String id;
    private final String password;
    private final String name;
    private final String email;

    public User(
            final String id,
            final String password,
            final String name,
            final String email
    ) throws IllegalArgumentException
    {
        validate(id, password, name, email);
        this.idx = auto_increment++;
        this.id = id.trim();
        this.password = SecurePassword.genPassword(password);
        this.name = name.trim();
        this.email = email.trim();
    }

    public void validate(
            final String id,
            final String password,
            final String name,
            final String email
    )
    {
        checkString("id", id, ID_REGEX, ID_MIN, ID_MAX);
        checkString("password", password, PW_REGEX, PW_MIN, PW_MAX);
        checkString("name", name, NAME_REGEX, NAME_MIN, NAME_MAX);
        checkString("email", email, EMAIL_REGEX, EMAIL_MIN, EMAIL_MAX);
    }

    private void checkString(
            final String name,
            final String str,
            final String regex,
            final int min,
            final int max
    )
    {
        Checker.checkIntMinMax(min, max);
        final String trimmed = str.trim();
        final int len = trimmed.length();
        if (!trimmed.matches(regex)) {
            throw new IllegalArgumentException(name + " is invalid!");
        }
        if (len < min || len > max) {
            throw new IllegalArgumentException(name + " length is out of bound [" + min + ", " + max + "]");
        }
    }

    public long getIdx() {
        return idx;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Auto-gen code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idx == user.idx && id.equals(user.id) && password.equals(user.password) && name.equals(user.name) && email.equals(user.email);
    }

    // Auto-gen code
    @Override
    public int hashCode() {
        return Objects.hash(idx, id, password, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "idx=" + idx +
                ", id='" + id + '\'' +
                ", password='<masked>" + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
