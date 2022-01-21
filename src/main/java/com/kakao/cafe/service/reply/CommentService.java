package com.kakao.cafe.service.reply;

import com.kakao.cafe.domain.Entity.Comment;
import com.kakao.cafe.domain.Repository.reply.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    public void save(Comment comment) {
        this.commentRepository.save(comment);
    }

    public List<Comment> findAllByArticleIdAndNotDeleted(int articleId) {
        return this.commentRepository.findAllByArticleIdAndNotDeleted(articleId);
    }

    public Comment findById(int commentId) {
        return this.commentRepository.findById(commentId);
    }

    public void delete(int commentId) {
        this.commentRepository.delete(commentId);
    }

}
