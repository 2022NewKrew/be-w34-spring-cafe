package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.ValidConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ArticleSaveForm {
    private Long authorId;
    private String author;
    @Size(max= ValidConst.MAX_ARTICLE_TITLE_LEN, message = ValidConst.ARTICLE_TITLE_MESSAGE)
    private String title;
    @Size(max= ValidConst.MAX_ARTICLE_CONTENT_LEN, message = ValidConst.ARTICLE_CONTENT_MESSAGE)
    private String content;

    public ArticleSaveForm() {}

    public void setAuthorInfo(User user) {
        author = user.getName();
        authorId = user.getId();
    }
}
