package com.kakao.cafe.article.application;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.dto.ArticleListResponse;
import com.kakao.cafe.article.dto.ArticleSaveRequest;
import com.kakao.cafe.article.dto.ArticleShowResponse;
import com.kakao.cafe.article.infra.ArticleJdbcRepository;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.user.domain.SessionedUser;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.infra.UserJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleJdbcRepository articleJdbcRepository, UserJdbcRepository userJdbcRepository) {
        this.articleRepository = articleJdbcRepository;
        this.userRepository = userJdbcRepository;
    }

    public void save(ArticleSaveRequest request) {
        log.info(this.getClass() + ": 게시글 작성");
        String authorId = request.writer;
        User author = userRepository.findByIdOrNull(authorId);
        if (author == null) {
            EntityNotFoundException.throwNotExistsByField(User.class, "userId", authorId);
        }
        Article article = request.toArticle(author);
        articleRepository.save(article);
    }

    public List<ArticleListResponse> findAll() {
        log.info(this.getClass() + ": 게시글 목록");
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleListResponse::valueOf).collect(Collectors.toList());
    }

    public ArticleShowResponse findById(int articleId) {
        log.info(this.getClass() + ": 게시글 상세보기");
        Article article = articleRepository.findByIdOrNull(articleId);
        if (article == null) {
            EntityNotFoundException.throwNotExistsByField(Article.class, "id", articleId);
        }
        return ArticleShowResponse.valueOf(article);
    }

    public void updateById(int articleId, ArticleSaveRequest request, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 게시글 수정");
        sessionedUser.validateSession(request.writer);
        String userId = sessionedUser.getUserId();
        Article article = articleRepository.findByIdOrNull(articleId);
        if (article == null) {
            EntityNotFoundException.throwNotExistsByField(Article.class, "articleId", articleId);
        }

        User user = article.getAuthor();
        if (user == null) {
            EntityNotFoundException.throwNotExistsByField(User.class, "userId", userId);
        }

        article.update(request.title, request.contents);
        articleRepository.update(article);
    }

    public void deleteById(int articleId, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 게시글 삭제");
        Article article = articleRepository.findByIdOrNull(articleId);
        String authorId = article.getAuthorId();
        sessionedUser.validateSession(authorId);

        articleRepository.delete(article);
    }
}
