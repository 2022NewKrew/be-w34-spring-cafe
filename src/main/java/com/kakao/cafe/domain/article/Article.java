package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

	private Integer index;
	private String createdDate;
	private String createdTime;
	private String writer;
	private User writerObj;
	private String title;
	private String contents;

}
