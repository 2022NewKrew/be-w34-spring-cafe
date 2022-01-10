package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Repository
public class ArticleMemoryRepository implements ArticleRepository {

    private Map<Long, Article> postList = new HashMap<>();

    @Override
    public void save(Article article) {
        postList.put(article.getPostId(), article);
    }

    @Override
    public List<Article> findAll() {
        return postList.values().stream().collect(Collectors.toList());
    }

    @Override
    public Article findById(Long postId) {
        return postList.get(postId);
    }
}
