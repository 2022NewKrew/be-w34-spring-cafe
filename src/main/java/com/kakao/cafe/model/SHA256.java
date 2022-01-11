package com.kakao.cafe.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author    : brody.moon
 * version   : 1.0
 * password 를 암호화해주는 클래스입니다.
 */
public class SHA256 {

    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
