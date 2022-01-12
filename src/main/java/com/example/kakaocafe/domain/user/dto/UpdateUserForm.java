package com.example.kakaocafe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserForm {
    private long id;
    private final String name;
}
