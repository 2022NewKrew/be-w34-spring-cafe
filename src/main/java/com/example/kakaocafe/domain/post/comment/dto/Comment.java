package com.example.kakaocafe.domain.post.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {
    private final long id;
    private final String writer;
    private final String contents;
    private final String created;
}
