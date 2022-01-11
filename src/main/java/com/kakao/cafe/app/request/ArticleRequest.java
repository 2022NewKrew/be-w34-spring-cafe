// NOTE 얘네도 네트워크 상에서 컨트롤러로 전달되는 DTO가 아닐까?
package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.DraftDto;

public class ArticleRequest {

    private final String author;
    private final String title;
    private final String content;

    public ArticleRequest(
            String writer,
            String title,
            String contents
    ) {
        this.author = writer;
        this.title = title;
        this.content = contents;
    }

    public DraftDto toDraftDto() {
        return new DraftDto(author, title, content);
    }
}
