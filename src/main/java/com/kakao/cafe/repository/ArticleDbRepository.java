package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class ArticleDbRepository implements ArticleRepository {
    @Override
    public Long save(Article entity) {

        return null;
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.empty();
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return null;
    }
}
