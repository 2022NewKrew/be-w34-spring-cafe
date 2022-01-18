package com.kakao.cafe.web.article.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleRegistrationForm {
    @NotNull(message = "글쓴이를 등록해야 합니다.")
    private final String writerName;

    @NotNull(message = "글의 제목을 입력해주세요.")
    @Size(max = 255, message = "글의 제목은 255자 이하입니다.")
    private final String title;

    @NotNull(message = "글의 본문을 입력해주세요.")
    private final String contents;

    public ArticleRegistrationForm(String writerName, String title, String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
