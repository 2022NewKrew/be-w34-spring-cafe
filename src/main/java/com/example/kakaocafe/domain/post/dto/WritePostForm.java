package com.example.kakaocafe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WritePostForm {
    private long writerId;
    private final String title;
    private final String contents;
}
