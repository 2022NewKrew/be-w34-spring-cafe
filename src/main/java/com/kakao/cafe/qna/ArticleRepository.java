package com.kakao.cafe.qna;

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
}
