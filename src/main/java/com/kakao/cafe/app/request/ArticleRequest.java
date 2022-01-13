// NOTE 얘네도 네트워크 상에서 컨트롤러로 전달되는 DTO가 아닐까?
package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.DraftDto;
import org.hibernate.validator.constraints.Length;

public class ArticleRequest {

    @Length(min=1, max=16)
    private final String author;

    @Length(min=1, max=64)
    private final String title;

    @Length(min=1, max=1024)
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
