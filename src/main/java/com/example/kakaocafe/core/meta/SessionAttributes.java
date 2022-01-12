package com.example.kakaocafe.core.meta;

public enum SessionAttributes {
    USER_KEY("userKey");

    private final String value;

    SessionAttributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
