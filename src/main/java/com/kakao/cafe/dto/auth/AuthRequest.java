package com.kakao.cafe.dto.auth;

public class AuthRequest {

    private final String username;
    private final String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AuthRequest) {
            AuthRequest request = (AuthRequest) obj;
            return username.equals(request.getUsername()) && password.equals(request.getPassword());
        }
        return false;
    }
}
