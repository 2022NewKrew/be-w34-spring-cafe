package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.CommentRepository;

import java.util.Optional;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public String postComment(Comment comment, Object sessionedUser) {
        validateNull(comment);
        comment.setAuthor(getAuthor(sessionedUser));
        commentRepository.saveComment(comment);
        return comment.getAuthor();
    }

    private void validateNull(Comment comment) {
        if (comment.getContent().isBlank()) {
            throw new IllegalArgumentException("댓글의 내용이 공백일 수는 없습니다.");
        }
    }

    private String getAuthor(Object sessionedUser) {
        User user = (User) sessionedUser;
        return user.getNickName();
    }

    public void checkAuthorMatch(Long commentId, User sessionedUser) throws IllegalAccessException {
        Comment originComment = commentRepository.findByCommentId(commentId).get();
        if (!sessionedUser.getNickName().equals(originComment.getAuthor())) {
            throw new IllegalAccessException("다른 사람의 댓글은 수정/삭제 할 수 없습니다.");
        }
    }

    public void edit(Long commentId, Comment comment) {
        validateNull(comment);
        commentRepository.editComment(commentId, comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteComment(commentId);
    }

    public Optional<Comment> findOne(Long id) {
        return commentRepository.findByCommentId(id);
    }
}
