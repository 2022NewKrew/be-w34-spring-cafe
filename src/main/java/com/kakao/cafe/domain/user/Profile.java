package com.kakao.cafe.domain.user;

import java.util.Objects;

public class Profile {

    private final String name;
    private final String email;

    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(name, profile.name) && Objects.equals(email, profile.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
