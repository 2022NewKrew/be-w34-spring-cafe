package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.repository.CreateArticleRequestDTO;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.FindAllUserResponseDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArticleService {

    ArticleRepository articleRepository = ArticleRepository.getRepository();

    public void createArticle(Long authorId, String title, String contents ){
        articleRepository.persist(new CreateArticleRequestDTO(title, authorId, contents));
    }

    public FindAllArticleResponseDTO getAllArticleViewDTO(Long startIndex, Long endIndex ){
        ArrayList<Article> articleList = articleRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > articleList.size()) {
            endIndex = articleList.size() + 1L;
        }
        if (startIndex > articleList.size() || startIndex >= endIndex) {
            return new FindAllArticleResponseDTO(new ArrayList<Article>());
        }
        Stream<Article> stream = articleList.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        return new FindAllArticleResponseDTO(stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new)));

    }


    public ArticleReadResponseDTO getArticleReadViewDTO(Long id) {
        articleRepository.increaseHit(id);
        Article article = articleRepository.find(id);
        return new ArticleReadResponseDTO(article.getId(), article.getAuthorId(), article.getDate(), article.getHits(), article.getContents());
    }
}
