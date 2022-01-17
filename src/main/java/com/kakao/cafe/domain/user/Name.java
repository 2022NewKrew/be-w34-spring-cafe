package com.kakao.cafe.domain.user;

public class Name {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Name)) {
            return false;
        }

        return name.equals(((Name) obj).getValue());
    }

    @Override
    public String toString() {
        return name;
    }
}
