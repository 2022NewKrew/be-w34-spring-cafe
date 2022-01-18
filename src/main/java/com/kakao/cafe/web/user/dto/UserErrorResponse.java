package com.kakao.cafe.web.user.dto;

public class UserErrorResponse {

    private String UserIdError;
    private String passwordError;
    private String nameError;
    private String EmailError;

    public String getUserIdError() {
        return UserIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getNameError() {
        return nameError;
    }

    public String getEmailError() {
        return EmailError;
    }

    public void setUserIdError(String userIdError) {
        UserIdError = userIdError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public void setEmailError(String emailError) {
        EmailError = emailError;
    }
}
