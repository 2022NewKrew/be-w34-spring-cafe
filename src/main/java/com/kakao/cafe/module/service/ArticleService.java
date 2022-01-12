package com.kakao.cafe.module.service;

import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.repository.ArticleRepository;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static com.kakao.cafe.module.model.mapper.ArticleMapper.*;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public void postArticle(ArticlePostDto articlePostDto) {
        Article article = toArticle(0L, findAuthor(articlePostDto.getAuthor()).getId(), articlePostDto.getTitle(),
                articlePostDto.getContents(), LocalDateTime.now(), 0, 0);
        articleRepository.addArticle(article);
    }

    public ArticleReadDto showArticle(Long id) {
        Article article = articleRepository.findArticleById(id);
        User author = userRepository.findUserById(article.getAuthorId());
        return toArticleReadDto(article, author);
    }

    private User findAuthor(String name) {
        return userRepository.findUserByName(name)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 작성자입니다."));
    }
}
