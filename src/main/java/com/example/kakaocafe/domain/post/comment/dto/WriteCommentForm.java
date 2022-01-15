package com.example.kakaocafe.domain.post.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteCommentForm {
    private long postId;
    private long writerId;
    private String contents;
}
