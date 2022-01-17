package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private List<Article> articleList;
    private Long articleId;

    public ArticleRepositoryImpl() {
        articleList = new ArrayList<>();
        articleId = 1L;
    }

    @Override
    public Long save(Article entity) {
        articleList.add(0, entity.init(articleId));
        return articleId++;
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return articleList.stream()
                .filter(article -> article.getArticleId().equals(articleId))
                .findFirst();
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        int totalPage = (int) Math.ceil(articleList.size() / (double) pageable.getSize());
        int fromIndex = pageable.getPage() * pageable.getSize();
        if (fromIndex >= articleList.size())
            return new Page<Article>(new ArrayList<>(), totalPage, articleList.size(), pageable);
        int toIndex = fromIndex + pageable.getSize();
        if (toIndex >= articleList.size())
            toIndex = articleList.size();
        return new Page<Article>(articleList.subList(fromIndex, toIndex), totalPage, articleList.size(), pageable);
    }
}
