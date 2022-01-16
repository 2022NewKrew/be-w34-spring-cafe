package com.example.kakaocafe.domain.user.dto;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
public class SignUpForm {
    private final String password;
    private final String name;
    private final String email;

    public SignUpForm(String password, String name, String email) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.name = name;
        this.email = email;
    }
}
