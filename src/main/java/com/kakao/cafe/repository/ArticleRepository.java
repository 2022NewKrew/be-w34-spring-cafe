package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article form);
    Article findByID(Long articleID);
    List<Article> findAll();

}
