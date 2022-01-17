package com.example.kakaocafe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostInfo {
    private final long id;
    private final String title;
    private final String contents;
}
