package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;

public class User {

    private final String userId;
    private final String password;
    private String email;
    private String name;

    public User(Builder builder) {
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    private static boolean checkBlankInString(String str) {
        return str.contains(" ");
    }

    public String getEmail() {
        return email;
    }

    private static boolean checkLengthOfString(String str) {
        return str.length() <= 0;
    }

    private static void checkUserId(String userId) throws IllegalUserIdException {
        if (checkLengthOfString(userId) || checkBlankInString(userId)) {
            throw new IllegalUserIdException("잘못된 ID 입니다.");
        }
    }

    private static void checkPassword(String password) throws IllegalPasswordException {
        if (checkLengthOfString(password) || checkBlankInString(password)) {
            throw new IllegalPasswordException("잘못된 password 입니다.");
        }
    }

    private static void checkUserName(String userName) throws IllegalUserNameException {
        if (checkLengthOfString(userName) || checkBlankInString(userName)) {
            throw new IllegalUserNameException("잘못된 이름 입니다.");
        }
    }

    private static void checkEmail(String email) throws IllegalEmailException {
        if (checkLengthOfString(email) || checkBlankInString(email)) {
            throw new IllegalEmailException("잘못된 email 주소 입니다.");
        }
    }

    public void setName(String name) throws IllegalUserNameException {
        checkUserName(name);
        this.name = name;
    }

    public void setEmail(String email) throws IllegalEmailException {
        checkEmail(email);
        this.email = email;
    }

    public static class Builder {

        private String userId;
        private String password;
        private String name;
        private String email;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build()
            throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
            checkUserId(userId);
            checkPassword(password);
            checkUserName(name);
            checkEmail(email);
            return new User(this);
        }
    }
}
