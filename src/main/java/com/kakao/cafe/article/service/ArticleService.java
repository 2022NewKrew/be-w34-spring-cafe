package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticleRequest;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void registerArticle(ArticleRequest articleRequest){
        articleRepository.save(Article.builder()
                .id((long) articleRepository.getNumberOfArticles() + 1)
                .author(articleRequest.getAuthor())
                .title(articleRequest.getTitle())
                .contents(articleRequest.getContents())
                .uploadDate(new Date())
                .build());

    }
}
