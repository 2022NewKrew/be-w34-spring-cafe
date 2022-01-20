package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserMapper;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private static final UserMapper MAPPER = UserMapper.INSTANCE;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle(String title, UserDto writerDto, String contents) {
        final Integer aid = articleRepository.articlesSize() + 1;
        final String uid = userRepository.findUidByUserId(writerDto.getUserId());
        final User writer = MAPPER.toUserEntity(writerDto, uid);
        final Article article = new Article(aid, title, writer, contents);
        articleRepository.createArticle(article);
        return article;
    }

    public List<Article> getArticles() {
        return articleRepository.findAllArticles();
    }

    public Article getArticleById(Integer aid) {
        if (!articleRepository.isArticleIdUsed(aid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 게시글을 찾을 수 없습니다.");
        }
        return articleRepository.findArticleByArticleId(aid);
    }
}
