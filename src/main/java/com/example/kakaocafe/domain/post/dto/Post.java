package com.example.kakaocafe.domain.post.dto;

import com.example.kakaocafe.domain.post.comment.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Post of(List<PostAndComment> postAndCommentList) {
        if (postAndCommentList.isEmpty()) {
            return null;
        }

        final List<Comment> comments = postAndCommentList.stream()
                .filter(PostAndComment::isNotEmptyComment)
                .map(Comment::of)
                .collect(Collectors.toList());

        final PostAndComment postAndComment = postAndCommentList.get(0);

        return new Post(
                postAndComment.getPostId(),
                postAndComment.getPostWriter(),
                postAndComment.getPostTitle(),
                postAndComment.getPostContents(),
                postAndComment.getPostCreated(),
                postAndComment.getViewCount(),
                comments);
    }
}
