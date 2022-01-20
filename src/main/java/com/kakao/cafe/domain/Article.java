package com.kakao.cafe.domain;


import com.kakao.cafe.dto.ArticleDTO;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String content;
    private static int articleCounts = 1;
    private int articleIndex;

    public static int createArticleIndex(){
        return ++articleCounts;
    }

}



