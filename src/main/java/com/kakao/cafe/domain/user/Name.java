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
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Name)) {
            return false;
        }

        Name other = (Name) obj;
        return name.equals(other.getValue());
    }

    @Override
    public String toString() {
        return name;
    }
}
