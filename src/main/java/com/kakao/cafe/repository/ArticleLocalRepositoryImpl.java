package com.kakao.cafe.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kakao.cafe.entity.Article;

@Repository
public class ArticleLocalRepositoryImpl implements ArticleRepository {
	private static Map<Integer, Article> articleMap = new HashMap<>();
	private static int sequence = 0;

	@Override
	public void save(Article article) {
		article.setId(++sequence);
		article.setCreateTime(LocalDateTime.now());
		articleMap.put(article.getId(), article);
	}

	@Override
	public List<Article> findAll() {
		return new ArrayList<>(articleMap.values());
	}

	@Override
	public Article findById(int id) {
		return articleMap.get(id);
	}
}
