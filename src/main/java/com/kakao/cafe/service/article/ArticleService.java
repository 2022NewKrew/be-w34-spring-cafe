package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.member.Member;

import java.util.List;

public interface ArticleService {

    List<Article> inquireAllArticles();

    void postArticle(Article member, String userId);

    Article inquireOneArticle(Long articleId);

    Article editArticle(Article article, Member member);

    void deleteArticle(Long articleId, Member loginMember);

    void deleteAllArticles();

    void checkAuthorization(Long article, Member member);
}
