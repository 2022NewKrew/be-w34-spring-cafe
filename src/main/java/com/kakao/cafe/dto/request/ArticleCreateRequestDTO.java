package com.kakao.cafe.dto.request;

import com.kakao.cafe.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleCreateRequestDTO {
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
