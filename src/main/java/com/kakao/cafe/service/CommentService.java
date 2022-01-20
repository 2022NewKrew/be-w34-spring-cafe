package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.comment.SaveCommentDto;
import com.kakao.cafe.dto.comment.CommentViewDto;

import java.util.List;

public interface CommentService {

    Comment findByUserIdAndPostId(Long userId, Long postId);

    List<CommentViewDto> getCommentViewDtoListByPostId(Long postId);

    Comment addComment(SaveCommentDto saveCommentDto, Long userId);

    Comment updateComment(SaveCommentDto saveCommentDto, Long userId);

    void deleteByCommentIdAndUserId(Long commentId, Long userId);
}
