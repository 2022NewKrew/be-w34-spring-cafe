package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

	private String writer;
	private String title;
	private String contents;
	private User writerObj;
	private String createdDate;
	private String createdTime;
}
