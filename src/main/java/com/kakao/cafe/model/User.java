package com.kakao.cafe.model;

import com.kakao.cafe.validator.user.EmailValidator;
import com.kakao.cafe.validator.user.NameValidator;
import com.kakao.cafe.validator.user.PasswordValidator;
import com.kakao.cafe.validator.user.UserIdValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {
    private final String userId;
    private String hashedPassword;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        validateUserId(userId);
        validatePassword(password);
        validateName(name);
        validateEmail(email);

        this.userId = userId;
        this.hashedPassword = hash(password);
        this.name = name;
        this.email = email;
    }

    private void validateUserId(String userId) {
        new UserIdValidator().validate(userId);
    }

    private void validatePassword(String password) {
        new PasswordValidator().validate(password);
    }

    private void validateName(String name) {
        new NameValidator().validate(name);
    }

    private void validateEmail(String email) {
        new EmailValidator().validate(email);
    }

    public void changePassword(String newPassword) {
        this.hashedPassword = hash(newPassword);
    }

    private String hash(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }
}
