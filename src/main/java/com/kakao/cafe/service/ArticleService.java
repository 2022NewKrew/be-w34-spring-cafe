package com.kakao.cafe.service;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.ArticleDetailResponseDto;
import com.kakao.cafe.web.dto.ArticleResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
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

    public void deleteArticle(Long id) {
        articleRepository.shiftIsDeleted(id);
    }
}
