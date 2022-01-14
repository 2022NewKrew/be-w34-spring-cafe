package com.kakao.cafe.domain.member;

import com.kakao.cafe.exception.ErrorMessages;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Name {

    private final String name;
    private static final String NAME_FORMAT = "[A-Za-z가-힣]*";

    public Name(String name) {
        if (!name.matches(NAME_FORMAT))
            throw new IllegalArgumentException(ErrorMessages.WRONG_NAME_FORMAT);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
