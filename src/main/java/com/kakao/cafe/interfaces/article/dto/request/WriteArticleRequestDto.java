package com.kakao.cafe.interfaces.article.dto.request;

import javax.validation.constraints.NotEmpty;

public class WriteArticleRequestDto {
    @NotEmpty(message = "글쓴이 입력 값이 비어있습니다")
    private final String writer;

    @NotEmpty(message = "제목 입력 값이 비어있습니다")
    private final String title;

    @NotEmpty(message = "본문 입력 값이 비어있습니다")
    private final String contents;

    public WriteArticleRequestDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
