package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.article.ArticleContentDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleWriteDto;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.repository.ArticleRepository;
import java.util.List;
import java.util.UUID;

import com.kakao.cafe.utils.DtoConversion;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    public ArticleService(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    public void registerArticle(ArticleWriteDto articleWriteDto) {
        User user = userService.findUserByUserName(new UserName(articleWriteDto.getUserName()));
        Article article = DtoConversion.articleWriteDtoToArticle(articleWriteDto, user);
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticleList() {
        return DtoConversion.articleListToArticleDtoList(articleRepository.findAll());
    }

    public ArticleContentDto findArticleById(UUID articleId) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        article.increaseViewCount();
        articleRepository.update(article);
        return DtoConversion.articleToArticleContentDto(article);
    }
}
