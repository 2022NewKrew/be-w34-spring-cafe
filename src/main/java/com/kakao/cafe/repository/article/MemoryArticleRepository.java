package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class MemoryArticleRepository implements ArticleRepository {

	private final List<Article> articles;
	private int maxIndex;

	@Override
	public void save(Article article) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		String formattedToday = today.format(dateFormatter);

		LocalTime now = LocalTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		String formattedNow = now.format(timeFormatter);

		article.setIndex(maxIndex++);
		article.setCreatedDate(formattedToday);
		article.setCreatedTime(formattedNow);

		articles.add(article);
	}

	@Override
	public List<Article> findAll() {
		return articles;
	}

	@Override
	public Article findByIndex(int index) {
		return articles.stream().filter(article -> article.getIndex() == index)
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
