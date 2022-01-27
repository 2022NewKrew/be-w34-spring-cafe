package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.exception.UnauthorizedException;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.DbArticleRepository;
import com.kakao.cafe.web.dto.article.ArticleResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private boolean hasLogInUser(HttpSession session) {
        return session.getAttribute("sessionUser") == null;
    }

    private void preventNotLoggedInUser(HttpSession session) {
        if (hasLogInUser(session))
            throw new UnauthorizedException("SESSION NOT LOGGED IN", ErrorCode.UNAUTHORIZED);
    }

    public ArticleService(DbArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(Article article, HttpSession session) {
        preventNotLoggedInUser(session);
        articleRepository.create(article);
    }

    public List<ArticleResponseDto> readAll() {
        return articleRepository.readAll().stream().map(
                article -> ArticleResponseDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .date(article.getDate())
                        .build()
        ).collect(Collectors.toList());
    }

    public ArticleResponseDto findById(String id) {
        Optional<Article> foundArticleOptional = articleRepository.read(Long.parseLong(id));
        Article foundArticle = foundArticleOptional.orElseThrow(() -> new IllegalArgumentException("Illegal ID Params: Article"));

        return ArticleResponseDto.builder()
                .title(foundArticle.getTitle())
                .id(foundArticle.getId())
                .content(foundArticle.getContent())
                .date(foundArticle.getDate())
                .build();
    }

    public void ArticleDetail(String articleIndex, HttpSession session, Model model) {
        preventNotLoggedInUser(session);
        model.addAttribute("article", findById(articleIndex));
    }


}
