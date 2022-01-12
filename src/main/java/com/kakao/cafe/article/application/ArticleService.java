package com.kakao.cafe.article.application;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.dto.ArticleListResponse;
import com.kakao.cafe.article.dto.ArticleSaveRequest;
import com.kakao.cafe.article.infra.ArticleRepositoryImpl;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.infra.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(ArticleSaveRequest request) {
        log.info(this.getClass() + ": 글쓰기");
        String authorName = request.writer;
        User author = userRepository.findByUserNameOrNull(authorName);
        Article article = request.toArticle(author);
        articleRepository.save(article);
    }

    public List<ArticleListResponse> findAll() {
        log.info(this.getClass() + ": 글 목록");
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleListResponse::valueOf)
                .collect(Collectors.toList());
    }
}
