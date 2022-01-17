package com.example.kakaocafe.domain.post.comment;

import com.example.kakaocafe.core.exception.HasNotPermissionException;
import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDAO commentDAO;

    @Transactional
    public void create(WriteCommentForm writeCommentForm) {
        commentDAO.create(writeCommentForm);
    }

    @Transactional
    public void delete(long postId, long commentId, long writerId) {
        ifIsNotWriterThrowException(postId, commentId, writerId);
        commentDAO.delete(commentId);
    }

    private void ifIsNotWriterThrowException(long postId, long commentId, long writerId) {
        if (commentDAO.isNotWriter(postId, commentId, writerId)) {
            throw new HasNotPermissionException();
        }
    }
}
