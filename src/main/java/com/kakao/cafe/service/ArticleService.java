package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDetailDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.repository.ArticleRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepository();
    private final ArticleTransformation articleTransformation = new ArticleTransformation();
    public List<ArticleListDto> getArticleList() {
        List<ArticleDao> articleList = articleRepository.selectAll();
        return articleList.stream().map(articleTransformation::toArticleListDto).collect(Collectors.toList());
    }

    public ArticleDetailDto getArticleDetailDto(Long id) {
        ArticleDao articleDao = articleRepository.select(id);
        return articleTransformation.toArticleDetailDto(articleDao);
    }

    public void createArticle(Article article) {
        articleRepository.insert(article);
    }

    public Article getArticle(ArticlePostDto articlePostDto, HttpSession session) {
        UserProfileDto userProfileDto = (UserProfileDto) session.getAttribute("sessionedUser");
        String id = userProfileDto.getId();
        String name = userProfileDto.getName();
        return articleTransformation.toArticle(articlePostDto, id, name);
    }

    public Boolean checkSameUser(ArticleDetailDto articleDetailDto, HttpSession session) {
        String articleId = articleDetailDto.getId();
        String sessionId = ((UserProfileDto) session.getAttribute("sessionedUser")).getId();
        return articleId.equals(sessionId);
    }

    public void updateArticle(Long id, ArticlePostDto articlePostDto) {
        articleRepository.update(id, articlePostDto);
    }
}
