package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDetailDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepository();
    private final ArticleTransformation articleTransformation = new ArticleTransformation();
    public List<ArticleListDto> getArticleList() {
        List<ArticleDao> articleList = articleRepository.selectAll();
        return articleList.stream().map(articleTransformation::toArticleListDto).collect(Collectors.toList());
    }

    public ArticleDetailDto getArticleDetailDto(Long id) {
        ArticleDao articleDao = articleRepository.select(id);
        return articleTransformation.toArticleDetailDto(articleDao);
    }

    public void createArticle(Article article) {
        articleRepository.insert(article);
    }
}
