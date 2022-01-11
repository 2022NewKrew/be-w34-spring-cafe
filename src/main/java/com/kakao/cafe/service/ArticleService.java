package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.MemoryArticleRepository;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.ArticleListResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    public void create(ArticleCreateRequestDto requestDto) {
        Article article = articleRepository.create(new Article(requestDto.getWriter(), requestDto.getTitle(), requestDto.getContents()));
        logger.info("{} 글 생성", article.getTitle());
    }

    public List<ArticleListResponseDto> findAll() {
        List<ArticleListResponseDto> responseDtoList = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            ArticleListResponseDto responseDto = new ArticleListResponseDto();
            responseDto.setWriter(article.getWriter());
            responseDto.setTitle(article.getTitle());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }
}
