package com.example.kakaocafe.domain.post.dto;


import com.example.kakaocafe.domain.post.comment.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private List<Comment> comments;

    public static Post of(PostAndComment postAndComment, List<Comment> comments) {
        return new Post(
                postAndComment.getPostId(),
                postAndComment.getPostWriter(),
                postAndComment.getPostTitle(),
                postAndComment.getPostContents(),
                postAndComment.getPostCreated(),
                postAndComment.getViewCount(),
                comments
        );
    }
}
