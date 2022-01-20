package com.kakao.cafe.qna.article;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
public interface ArticleRepository {

    Article save(Article article);
    Article findArticleById(Integer id);
    List<Article> findAll();

    default Article updateContents(Article article) {
        return null;
    }
    default Integer updateCommentsCount(Integer articleId, Integer commentsCount) {
        return null; }
}
