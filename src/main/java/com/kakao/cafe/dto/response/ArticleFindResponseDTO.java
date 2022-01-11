package com.kakao.cafe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ArticleFindResponseDTO {
	private final int id;
	private final String writer;
	private final String title;
	private final String contents;
	private final String createTime;
}
