package com.kakao.cafe.dto;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
public class ArticleCreateDto {

    private User user;
    @NotBlank(message = "제목을 입력해주세요")
    private final String title;
    @NotBlank(message = "내용을 입력해주세요")
    private final String contents;
    private Integer id = 0;

    public ArticleCreateDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Integer id) { this.id = id; }

    public Article toEntity() {
        return Article.builder().id(id)
                .user(User.builder()
                        .id(user.getId())
                        .userId(user.getUserId())
                        .password(user.getPassword())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .build())
                .title(title)
                .contents(contents)
                .createTime(LocalDateTime.now())
                .build();
    }

    @Override
    public String toString() {
        return "ArticleCreateDto{" +
                "user=" + user +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
