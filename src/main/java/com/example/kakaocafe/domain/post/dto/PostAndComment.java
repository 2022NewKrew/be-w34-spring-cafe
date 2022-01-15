package com.example.kakaocafe.domain.post.dto;

import com.example.kakaocafe.domain.post.comment.CommentDAO;
import com.example.kakaocafe.domain.post.comment.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    public Post toPost(List<Comment> comments) {
        return new Post(postId, postWriter, postTitle, postContents, postCreated, comments);
    }

    public Comment toComment() {
        return new Comment(commentId, commenter, commentContents, commentCreated);
    }
}
