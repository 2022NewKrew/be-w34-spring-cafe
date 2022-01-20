package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.CommentRequest;
import com.kakao.cafe.article.model.Comment;
import com.kakao.cafe.article.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public void addComment(Long articleId, String author, CommentRequest commentRequest) {
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setArticleId(articleId);
        comment.setAuthor(author);

        commentRepository.save(comment);
    }
}
