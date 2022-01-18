package com.kakao.cafe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ArticleDTO {
    private long key;
    private UserDTO author;
    private String title;
    private String content;
    private String postTime;
}
