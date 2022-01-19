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
import java.time.LocalDateTime;
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
        User foundUser = userRepository.findUserByUid(authInfo.getUid())
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_FOUND, authInfo.getUid()));

        Article article = Article.builder()
            .uid(foundUser.getUid())
            .title(createDTO.getTitle())
            .body(createDTO.getBody())
            .createdAt(LocalDateTime.now())
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
        Article foundArticle = articleRepository.findArticleById(id)
            .orElseThrow(() -> new ArticleNotFoundException(ErrorCode.NOT_FOUND, id));

        logger.info("Read Article by [ID : {}] :: {}", id, foundArticle);
        return Result.from(foundArticle);
    }
}
