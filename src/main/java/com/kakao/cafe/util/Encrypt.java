package com.kakao.cafe.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Encrypt {

    private final MessageDigest md;

    public Encrypt() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-512");
        md.update(generateSalt());
    }

    public String encrypt(String password) {
        return Arrays.toString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);
        return salt;
    }
}
