package com.kakao.cafe.qna.comment;

import com.kakao.cafe.qna.BaseEntity;
import lombok.Getter;
import lombok.experimental.Delegate;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 5:40
 */
@Getter
public class Comment  {

    private Integer writerId;
    private Integer articleId;

    @Delegate
    private BaseEntity baseEntity;

    public Comment(Integer writerId, Integer articleId, String writer, String contents) {
        this.baseEntity = new BaseEntity(writer, contents);
        this.writerId = writerId;
        this.articleId = articleId;
    }

    public Comment(Integer id,
                   Integer writerId,
                   String writer,
                   String contents,
                   Integer articleId,
                   Boolean isDeleted,
                   LocalDateTime createdDate,
                   LocalDateTime modifiedDate) {
        this.baseEntity = new BaseEntity(id, writer, contents, isDeleted, createdDate, modifiedDate);
        this.writerId = writerId;
        this.articleId = articleId;
    }

    public void deleteComment() {
        this.baseEntity.deleteEntity();
    }
}
