package com.kakao.cafe.article.dto.request;

import com.kakao.cafe.article.entity.Article;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ArticleUpdateRequestDTO {
	private final String writer;
	private final String title;
	private final String contents;

	public Article toEntity() {
		return Article.builder()
			.writer(writer)
			.title(title)
			.contents(contents)
			.build();
	}
}
