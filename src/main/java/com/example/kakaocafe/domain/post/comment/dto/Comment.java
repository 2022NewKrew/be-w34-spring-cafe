package com.example.kakaocafe.domain.post.comment.dto;


import com.example.kakaocafe.domain.post.dto.PostAndComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {
    private final long id;
    private final String writer;
    private final String contents;
    private final String created;

    public static Comment of(PostAndComment postAndComment) {
        return new Comment(
                postAndComment.getCommentId(),
                postAndComment.getCommenter(),
                postAndComment.getCommentContents(),
                postAndComment.getCommentCreated()
        );
    }
}
