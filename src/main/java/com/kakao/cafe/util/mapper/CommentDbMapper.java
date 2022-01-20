package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.CommentDbDto;

public class CommentDbMapper {

    public static CommentDbDto toDto(Comment comment) {
        return new CommentDbDto.Builder()
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .text(comment.getText())
                .id(comment.getId())
                .build();
    }


    public static Comment toComment(CommentDbDto commentDbDto, Post post, User user) {
        return new Comment.Builder()
                .post(post)
                .user(user)
                .id(commentDbDto.getId())
                .text(commentDbDto.getText())
                .build();
    }
}
