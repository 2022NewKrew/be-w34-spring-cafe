package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

	private Integer index;
	private String createdDate;
	private String createdTime;
	private String writer;
	private String title;
	private String contents;
}
