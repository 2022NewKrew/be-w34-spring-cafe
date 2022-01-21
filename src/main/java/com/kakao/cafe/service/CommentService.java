package com.kakao.cafe.service;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.wrappable.CommentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comments findAll(long postId) {
        return commentRepository.findAll(postId);
    }

    public int insert(Comment comment) {
        return commentRepository.insert(comment);
    }

    public Comment findById(long id) {
        try {
            return commentRepository.findById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new CommentNotFoundException(exception, id);
        }
    }

    public int delete(Comment comment, String userId) {
        if (!comment.isUser(userId))
            throw new UnauthorizedActionException(
                    String.format("다른 유저의 comment를 삭제할 수 없습니다! 당신 : %s , commenter : %s", userId, comment.getUser().getId()));
        return commentRepository.delete(comment.getId());
    }

    public int update(Comment comment, String userId) {
        if (!comment.isUser(userId))
            throw new UnauthorizedActionException("다른 유저의 comment를 수정할 수 없습니다!");
        return commentRepository.update(comment);
    }


}
