package com.kakao.cafe.user.dto.request;

import org.springframework.lang.NonNull;

public class UserCreateRequest {
    @NonNull
    private final String userId;
    @NonNull
    private final String password;
    @NonNull
    private final String name;
    @NonNull
    private final String email;

    public UserCreateRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() { return this.userId; }
    public String getPassword() { return this.password; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
}
