package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.MemoryArticleRepository;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import com.kakao.cafe.web.dto.ArticleDetailResponseDto;
import com.kakao.cafe.web.dto.ArticleListResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    public void create(ArticleCreateRequestDto requestDto) {
        Long id = articleRepository.generateId();
        Article article = articleRepository.create(new Article(id, requestDto.getWriter(), requestDto.getTitle(), requestDto.getContents()));
        logger.info("{} 글 생성", article.getTitle());
    }

    public List<ArticleListResponseDto> findAll() {
        List<ArticleListResponseDto> responseDtoList = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            ArticleListResponseDto responseDto = new ArticleListResponseDto();
            responseDto.setId(article.getId());
            responseDto.setWriter(article.getWriter());
            responseDto.setTitle(article.getTitle());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    public ArticleDetailResponseDto findById(Long id) {
        ArticleDetailResponseDto responseDto = new ArticleDetailResponseDto();
        Optional<Article> foundArticle = articleRepository.findById(id);
        if (foundArticle.isPresent()) {
            responseDto.setId(foundArticle.get().getId());
            responseDto.setWriter(foundArticle.get().getWriter());
            responseDto.setTitle(foundArticle.get().getTitle());
            responseDto.setContents(foundArticle.get().getContents());
            return responseDto;
        }
        return null;
    }
}
