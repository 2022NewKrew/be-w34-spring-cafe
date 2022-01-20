package com.kakao.cafe.service;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public void postArticle(ArticleCreateRequestDto requestDto) {
        articleRepository.create(requestDto);
        logger.info("{} 글 생성", requestDto.getTitle());
    }

    public List<ArticleResponseDto> getArticleList() {
        return ArticleResponseDto.from(articleRepository.findNotDeleted());
    }

    public ArticleDetailResponseDto getArticleDetail(Long id) {
        return ArticleDetailResponseDto.from(articleRepository.findById(id));
    }

    public void deleteArticle(Long id, String userId) {
        articleRepository.shiftIsDeleted(id, userId);
    }

    public List<CommentResponseDto> getArticleComments(Long id) {
        return CommentResponseDto.from(commentRepository.findByArticleIdNotDeleted(id));
    }

    public void postComment(CommentCreateRequestDto requestDto) {
        commentRepository.create(requestDto);
    }
}
