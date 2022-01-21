package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.dto.CommentPostUserDbDto;

public class CommentDbMapper {

    public static CommentDbDto toDto(Comment comment) {
        return new CommentDbDto.Builder()
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .text(comment.getText())
                .id(comment.getId())
                .build();
    }


    public static Comment toComment(CommentPostUserDbDto commentPostUserDbDto) {
        Post post = new Post.Builder()
                .id(commentPostUserDbDto.getPostId())
                .contents(commentPostUserDbDto.getContents())
                .title(commentPostUserDbDto.getTitle())
                .writer(commentPostUserDbDto.getUserId())
                .build();

        User user = new User.Builder()
                .name(commentPostUserDbDto.getName())
                .id(commentPostUserDbDto.getUserId())
                .password(commentPostUserDbDto.getPassword())
                .email(commentPostUserDbDto.getEmail())
                .build();

        return new Comment.Builder()
                .post(post)
                .user(user)
                .id(commentPostUserDbDto.getId())
                .text(commentPostUserDbDto.getText())
                .build();
    }
}
