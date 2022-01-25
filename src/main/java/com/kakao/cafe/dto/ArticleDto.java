package com.kakao.cafe.dto;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class ArticleDto {

    private Integer id = 0;
    private User user;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String contents;
    private String createTime;

    public ArticleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


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

}
