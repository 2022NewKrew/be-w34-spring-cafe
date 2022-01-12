package com.example.kakaocafe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Post {
    private final long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String created;
}
