package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDTO.Create;
import com.kakao.cafe.dto.ArticleDTO.Result;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.ArticleRepository;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(Create createDTO, AuthInfo authInfo) {
        Optional<User> foundUser = userRepository.findUserByUid(authInfo.getUid());
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.NOT_FOUND, authInfo.getUid());
        }

        Article article = Article.builder()
            .uid(foundUser.get().getUid())
            .title(createDTO.getTitle())
            .body(createDTO.getBody())
            .build();

        articleRepository.save(article);
        logger.info("Article Created : {}", article);
    }

    @Transactional(readOnly = true)
    public List<Result> readAll() {
        List<Article> articles = articleRepository.findAllArticles();
        logger.info("Read All Articles {}", articles);
        return articles.stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public Result readById(Long id) {
        Optional<Article> foundArticle = articleRepository.findArticleById(id);
        if (foundArticle.isEmpty()) {
            throw new ArticleNotFoundException(ErrorCode.NOT_FOUND, id);
        }

        logger.info("Read Article by [ID : {}] :: {}", id, foundArticle.get());
        return Result.from(foundArticle.get());
    }
}
