package com.kakao.cafe.user.domain;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserId {

    private final UUID uuid;

    public UserId(String userId) {
        validateUserIdFormat(userId);
        this.uuid = UUID.fromString(userId);
    }

    public UserId(UUID userId) {
        this.uuid = userId;
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID());
    }

    private void validateUserIdFormat(String userId) {
        final String UUID_REGEX = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
        final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

        Matcher matcher = UUID_PATTERN.matcher(userId);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong UserId Format");
        }
    }

    public UUID getUUID() {
        return uuid;
    }
}
