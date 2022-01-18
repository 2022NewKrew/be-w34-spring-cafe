package com.kakao.cafe.article.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Article {
	private int id;
	private String writer;
	private String title;
	private String contents;
	private LocalDateTime createTime;

	public void setId(int id) {
		this.id = id;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
}
