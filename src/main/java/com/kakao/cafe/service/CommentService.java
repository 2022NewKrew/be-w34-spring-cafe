package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.CommentDTO;
import com.kakao.cafe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public Optional<Comment> findByKey(Long key) {
        return commentRepository.selectByKey(key);
    }
    public List<Comment> findAllByArticleKey(Long articleKey) {
        return commentRepository.selectByArticleKey(articleKey);
    }

    public Long join(Long articleKey, Long authorKey, String content) {
        Comment comment = Comment.builder()
                .content(content)
                .postTime(LocalDateTime.now())
                .build();
        return commentRepository.insert(articleKey, authorKey, comment);
    }

    public void update(Long key, String content) {
        commentRepository.update(key, content);
    }

    public void delete(Long key) {
        commentRepository.delete(key);
    }

    public void deleteByArticleKey(Long key) {
        commentRepository.deleteByArticleKey(key);
    }
}
