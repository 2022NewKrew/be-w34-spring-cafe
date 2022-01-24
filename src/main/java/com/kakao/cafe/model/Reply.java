package com.kakao.cafe.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Reply {

    private Integer id;
    private Integer articleId;
    private User user;
    private String contents;
    private String createTime;


    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", writer='" + user + '\'' +
                ", contents='" + contents + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
