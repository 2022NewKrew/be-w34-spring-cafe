package com.example.kakaocafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfile {
    private final String email;
    private final String name;
}
