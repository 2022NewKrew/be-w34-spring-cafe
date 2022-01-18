package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * author    : brody.moon
 * version   : 1.0
 * DB 대신 임시로 사용하는 저장소입니다.
 *
 */
public class ArticleNoBdUseRepository implements Repository<Article, ArticleDTO, Integer> {
    private static final Map<Integer, Article> DB = new HashMap<>();
    private int id = 0;

    @Override
    public Article save(ArticleDTO articleDTO) {
        articleDTO.setId(id);
        articleDTO.setComments(new ArrayList<>());

        Article article = new Article(articleDTO);
        DB.put(id++, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return Optional.ofNullable(DB.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(DB.values());
    }

    @Override
    public void delete(Integer id) {

    }

    public void clearStore() {
        DB.clear();
    }
}
