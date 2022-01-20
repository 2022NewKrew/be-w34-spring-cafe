package com.kakao.cafe.qna.comment;

import com.kakao.cafe.qna.BaseEntity;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 5:40
 */
@Getter
public class Comment extends BaseEntity {

    private Integer writerId;
    private Integer articleId;

    public Comment(Integer writerId, Integer articleId, String writer, String contents) {
        super(writer, contents);
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
        super(id, writer, contents, isDeleted, createdDate, modifiedDate);
        this.writerId = writerId;
        this.articleId = articleId;
    }

    public void deleteComment() {
        deleteEntity();
    }
}
