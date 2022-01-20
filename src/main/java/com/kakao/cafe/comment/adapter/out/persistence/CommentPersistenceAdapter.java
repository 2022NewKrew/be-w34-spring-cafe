package com.kakao.cafe.comment.adapter.out.persistence;

import com.kakao.cafe.comment.application.port.out.CreateCommentPort;
import com.kakao.cafe.comment.application.port.out.DeleteCommentPort;
import com.kakao.cafe.comment.application.port.out.LoadCommentPort;
import com.kakao.cafe.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements CreateCommentPort, DeleteCommentPort, LoadCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.delete(id);
    }

    @Override
    public List<Comment> findByQuestionId(Long questionPostId) {
        return commentRepository.findByQuestionPostId(questionPostId);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
}
