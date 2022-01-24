package com.kakao.cafe.dto;

import com.kakao.cafe.model.Reply;
import com.kakao.cafe.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class ReplyDto {
    private Integer id;
    private Integer articleId;
    private User user;
    private String contents;
    private LocalDateTime createTime;

    public ReplyDto(Integer articleId, User user, String contents) {
        this.articleId = articleId;
        this.user = user;
        this.contents = contents;
        this.createTime = LocalDateTime.now();
    }

    public Reply toEntity(){
        return Reply.builder()
                .id(0)
                .articleId(articleId)
                .user(user)
                .contents(contents)
                .createTime(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    @Override
    public String toString() {
        return "ReplyDto{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", user=" + user +
                ", contents='" + contents + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
