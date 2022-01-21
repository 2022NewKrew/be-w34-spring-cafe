package com.kakao.cafe.domain.comment;

import com.kakao.cafe.domain.comment.dto.CommentRawDataDto;
import com.kakao.cafe.domain.comment.repository.JdbcTemplateCommentRepository;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.global.error.exception.NoPermissionException;
import com.kakao.cafe.global.error.exception.NoSuchArticleException;
import com.kakao.cafe.global.error.exception.NoSuchCommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JdbcTemplateCommentRepository commentRepository;

    public boolean deleteComment(Long commentId, User user) {
        checkUserPermission(commentId, user);
        commentRepository.deleteById(commentId);
        return true;
    }

    private void checkUserPermission(Long commentId, User user) {
        CommentRawDataDto findCommentRawDataDto = commentRepository.findById(commentId).orElseThrow(NoSuchCommentException::new);
        if (!user.getId().equals(findCommentRawDataDto.getWriterLongId())) { throw new NoPermissionException(); }
    }
}
