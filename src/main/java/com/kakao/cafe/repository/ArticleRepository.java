package com.kakao.cafe.repository;

import java.util.List;

import com.kakao.cafe.entity.Article;

public interface ArticleRepository {
	void save(Article article);

	List<Article> findAll();

	Article findById(int id);
}
