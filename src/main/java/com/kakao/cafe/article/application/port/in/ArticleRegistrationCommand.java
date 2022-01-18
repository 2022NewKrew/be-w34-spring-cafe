package com.kakao.cafe.article.application.port.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleRegistrationCommand {
    @NotNull(message = "글의 제목을 입력해주세요.")
    @Size(max = 255, message = "글의 제목은 255자 이하입니다.")
    private final String title;

    @NotNull(message = "글의 본문을 입력해주세요.")
    private final String contents;

    public ArticleRegistrationCommand(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
