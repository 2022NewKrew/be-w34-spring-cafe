package com.kakao.cafe.article.domain;

import java.sql.Timestamp;

public class ArticleFactory {
     private static Long TIME_NOT_NEEDED = 0L;
     private static Long SEQUENCE_NOT_NEEDED = 0L;

    public static Article getArticle(String userId, String name, String title, String contents){
        return new Article(userId,
                            name,
                            title,
                            contents,
                            new Timestamp(TIME_NOT_NEEDED),
                            SEQUENCE_NOT_NEEDED);
    }
}
