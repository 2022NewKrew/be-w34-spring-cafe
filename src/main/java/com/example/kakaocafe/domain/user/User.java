package com.example.kakaocafe.domain.user;

import com.example.kakaocafe.domain.base.BaseEntity;
import lombok.Builder;

import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;

public class User extends BaseEntity {
    private final String email;
    private final String password;
    private final String name;

    @Builder
    private User(Long id, LocalDateTime created, String password, String name, String email) {
        super(id, created);
        this.password = password;
        this.name = name;
        this.email = email;
    }


    public void validatePassword(String plainPassword) throws LoginException {
        if (!BCrypt.checkpw(plainPassword, password)) {
            throw new LoginException();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
