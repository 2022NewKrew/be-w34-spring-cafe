package com.example.kakaocafe.domain.post.dto;


import com.example.kakaocafe.domain.post.comment.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Post {
    private final long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String created;
    private final long viewCount;
    private List<Comment> comments = new ArrayList<>();

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
