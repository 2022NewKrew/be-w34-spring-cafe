package com.kakao.cafe.model;

import com.kakao.cafe.dto.ArticleDto;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
public class Article {

    private Integer id;
    private User user;
    private String title;
    private String contents;
    private LocalDateTime createTime;

    @Builder
    public Article(Integer id, User user, String title, String contents, LocalDateTime createTime) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public ArticleDto toDto(){
        ArticleDto articleDto = new ArticleDto(title,contents);
        articleDto.setId(id);
        articleDto.setUser(user);
        articleDto.setCreateTime(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return articleDto;
    }
}
