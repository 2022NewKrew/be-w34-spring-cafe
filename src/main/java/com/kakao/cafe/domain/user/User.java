package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;

public class User {


    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        checkUserId(userId);
        checkPassword(password);
        checkUserName(name);
        checkEmail(email);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    private boolean checkBlankInString(String str) {
        return str.contains(" ");
    }

    private boolean checkLengthOfString(String str) {
        return str.length() <= 0;
    }

    private void checkUserId(String userId) throws IllegalUserIdException {
        if (checkLengthOfString(userId) || checkBlankInString(userId)) {
            throw new IllegalUserIdException("잘못된 ID 입니다.");
        }
    }

    private void checkPassword(String password) throws IllegalPasswordException {
        if (checkLengthOfString(password) || checkBlankInString(password)) {
            throw new IllegalPasswordException("잘못된 password 입니다.");
        }
    }

    private void checkUserName(String userName) throws IllegalUserNameException {
        if (checkLengthOfString(userName) || checkBlankInString(userName)) {
            throw new IllegalUserNameException("잘못된 이름 입니다.");
        }
    }

    private void checkEmail(String email) throws IllegalEmailException {
        if (checkLengthOfString(email) || checkBlankInString(email)) {
            throw new IllegalEmailException("잘못된 email 주소 입니다.");
        }
    }
}
