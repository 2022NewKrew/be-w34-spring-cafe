package com.kakao.cafe.users;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import static org.springframework.beans.BeanUtils.copyProperties;

public class User {

    private long seq;
    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Builder
    private User (long seq, @NotNull String id, @NotNull String password, @NotNull String name, @NotNull String email) {
        this.seq = seq;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public long getSeq() {
        return seq;
    }

    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getEmail() {
        return email;
    }

}
