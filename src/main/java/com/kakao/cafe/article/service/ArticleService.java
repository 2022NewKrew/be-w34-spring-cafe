package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public int createArticle(ArticleCreateRequest req) {
        Article article = new Article(req);

        return this.articleRepository.save(article);
    }

    public List<ArticleDetailResponse> getArticleList() {
        List<Article> articleList = this.articleRepository.findAll();

        return articleList.stream()
                          .map(ArticleDetailResponse::new)
                          .collect(Collectors.toUnmodifiableList());
    }

    public ArticleDetailResponse getArticleDetail(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);

        return new ArticleDetailResponse(
                article.orElseThrow(ArticleNotFoundException::new)
        );
    }
}
