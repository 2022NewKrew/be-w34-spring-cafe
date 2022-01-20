package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.exception.CommentNotFoundException;
import com.kakao.cafe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository jdbcCommentRepositoryImpl) {
        this.commentRepository = jdbcCommentRepositoryImpl;
    }

    @Transactional
    public void createComment(Integer qnaId, String writer, String contents) {
        Comment comment = new Comment(qnaId, writer, contents);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Integer commentId, String contents, String userId) throws AccessDeniedException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));

        if (!comment.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("해당 댓글을 수정할 권한이 없습니다.");
        }

        comment.updateContents(contents);
        commentRepository.save(comment);
    }

    @Transactional
    public CommentDto.ReadCommentForUpdateResponse readCommentForUpdate(Integer commentId, String userId) throws AccessDeniedException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));

        if (!comment.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("해당 댓글을 수정할 권한이 없습니다");
        }

        return CommentDto.ReadCommentForUpdateResponse.of(comment);
    }

    @Transactional
    public void deleteComment(Integer commentId, String userId) throws AccessDeniedException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));

        if (!comment.isValidDeleteUser(userId)) {
            throw new AccessDeniedException("해당 댓글을 삭제할 권한이 없습니다");
        }

        comment.delete();
        commentRepository.save(comment);
    }
}
