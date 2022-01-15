package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.FindArticleDto;
import com.kakao.cafe.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ArticleUseCase {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void createArticle(CreateArticleDto createArticleDto) {
        articleRepository.create(createArticleDto);
    }

    @Override
    public Optional<Article> getArticleById(FindArticleDto findArticleDto) {
        return articleRepository.findById(findArticleDto);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.getAll();
    }
}
