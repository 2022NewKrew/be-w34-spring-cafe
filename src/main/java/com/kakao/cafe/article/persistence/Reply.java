package com.kakao.cafe.article.persistence;

import com.kakao.cafe.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class Reply {

    private Long id;
    private User writer;
    private String content;
    private LocalDateTime createdAt;
}
