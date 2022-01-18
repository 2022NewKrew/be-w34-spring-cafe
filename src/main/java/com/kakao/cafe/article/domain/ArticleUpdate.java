package com.kakao.cafe.article.domain;

import com.kakao.cafe.common.BaseEntity;
import com.kakao.cafe.common.exception.ArticleUpdateException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleUpdate extends BaseEntity {
    private final String writerName;
    private final String title;
    private final String contents;

    public ArticleUpdate(Long id, String writerName, String title, String contents, LocalDateTime createdTime) {
        super(id, createdTime);
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public void validateUpdateAuth(String nicknameByUserId) {
        if (!writerName.equals(nicknameByUserId)){
            throw new ArticleUpdateException();
        }
    }
}
