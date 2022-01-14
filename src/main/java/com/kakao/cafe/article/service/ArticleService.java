package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.request.WriteArticleRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.dto.response.ArticleResponse;
import com.kakao.cafe.article.dto.response.ArticlesResponse;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private static final String USER_NOT_FOUNT_MESSAGE = "해당 회원정보를 찾을 수 없습니다.";
    private static final String ARTICLE_NOT_FOUNT_MESSAGE = "해당 글정보를 찾을 수 없습니다.";

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticleResponse save(WriteArticleRequest writeArticleRequest) {
        User user = userRepository.findById(writeArticleRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUNT_MESSAGE));
        Article article = writeArticleRequest.toArticle(articleRepository.autoIncrement(), user);
        return ArticleResponse.of(articleRepository.save(article));
    }

    public ArticlesResponse findAll() {
        return ArticlesResponse.of(articleRepository.findAll());
    }

    public ArticleDetailResponse findById(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException(ARTICLE_NOT_FOUNT_MESSAGE));
        article.incrementViewNum();
        return ArticleDetailResponse.of(article);
    }
}
