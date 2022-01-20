package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemoryArticleRepository implements ArticleRepository {

	private final List<Article> articles;
	private int maxIndex;

	@Override
	public void save(Article article) {
		article.setIndex(maxIndex++);

		articles.add(article);
	}

	@Override
	public List<Article> findAll() {
		return articles;
	}

	@Override
	public Article findByIndex(int index) {
		return articles.stream().filter(article -> article.getIndex() == index)
				.findAny().get();
	}

	@Override
	public void update(Article article) {

	}
}
