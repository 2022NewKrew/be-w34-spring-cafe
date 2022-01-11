package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDTO.Create;
import com.kakao.cafe.dto.ArticleDTO.Result;
import com.kakao.cafe.error.ArticleError;
import com.kakao.cafe.error.UserError;
import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public void create(Create createDTO) {
        Optional<User> foundUser = userRepository.findUserByName(createDTO.getAuthor());
        if (foundUser.isEmpty()) {
            logger.error("User Name : {} {}", createDTO.getAuthor(), UserError.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                UserError.NOT_FOUND.getMessage());
        }

        Article article = Article.of(foundUser.get(), createDTO.getTitle(), createDTO.getBody());

        articleRepository.add(article);
        logger.info("Article Created : {}", article);
    }

    public List<Result> readAll() {
        return articleRepository.findAllArticles().stream()
            .map(Result::from)
            .collect(Collectors.toUnmodifiableList());
    }

    public Result readById(Long id) {
        Optional<Article> foundArticle = articleRepository.findArticleById(id);
        if (foundArticle.isEmpty()) {
            logger.error("Article ID : {} {}", id, ArticleError.NOT_FOUND.getMessage());

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                ArticleError.NOT_FOUND.getMessage());
        }

        return Result.from(foundArticle.get());
    }
}
