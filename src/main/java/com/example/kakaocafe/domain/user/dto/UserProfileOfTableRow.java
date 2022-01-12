package com.example.kakaocafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileOfTableRow {
    private final String email;
    private final String name;
    private final String created;
}
