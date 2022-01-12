package com.example.kakaocafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpForm {
    private final String password;
    private final String name;
    private final String email;
}
