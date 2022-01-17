package com.example.kakaocafe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostForm {
    private long postId;
    private long writerId;
    private final String title;
    private final String contents;
}
