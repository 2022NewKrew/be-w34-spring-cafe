package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.dto.response.ArticleResDto;
import com.kakao.cafe.article.entity.ArticleEntity;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public void saveArticle(ArticleReqDto articleDto) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .writer(articleDto.getWriter())
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .build();

        articleRepository.save(articleEntity);
    }

    public List<ArticleResDto> getAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();
        return articleMapper.toArticleResDtoList(articleEntities);
    }

    public ArticleDetailResDto getArticle(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow();

        return articleMapper.toArticleDetailResDto(articleEntity);
    }
}
