package com.kakao.cafe.dto;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindUserDto {

    private final UUID userId;

    public FindUserDto(String userId) {
        validateUserIdFormat(userId);
        this.userId = UUID.fromString(userId);
    }

    private void validateUserIdFormat(String userId) {
        final String UUID_REGEX = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
        final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

        Matcher matcher = UUID_PATTERN.matcher(userId);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong UserId Format");
        }
    }

    public UUID getUserId() {
        return userId;
    }
}
