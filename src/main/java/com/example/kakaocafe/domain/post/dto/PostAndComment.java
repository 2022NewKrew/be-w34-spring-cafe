package com.example.kakaocafe.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostAndComment {
    private final long postId;
    private final String postWriter;
    private final String postTitle;
    private final String postContents;
    private final String postCreated;
    private final long commentId;
    private final String commenter;
    private final String commentContents;
    private final String commentCreated;
    private final long viewCount;
}
