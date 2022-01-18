package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleRepository {

	void save(Article article);

	List<Article> findAll();

	Article findByIndex(int index);
}
