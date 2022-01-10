package com.kakao.cafe.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.Objects;

public class SecurePassword {

    private SecurePassword() {}

    public static String genPassword(final String rawPassword) {
        Objects.requireNonNull(rawPassword);
        return BCrypt.withDefaults().hashToString(12, rawPassword.trim().toCharArray());
    }

    public static boolean verify(final String bcryptString, final String rawPassword) {
        Objects.requireNonNull(bcryptString);
        Objects.requireNonNull(rawPassword);
        return BCrypt.verifyer().verify(rawPassword.trim().toCharArray(), bcryptString).verified;
    }
}
