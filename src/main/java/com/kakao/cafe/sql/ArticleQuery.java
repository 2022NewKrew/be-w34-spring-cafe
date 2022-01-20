package com.kakao.cafe.sql;

public class ArticleQuery {
    static public String addArticleQuery(){
        return "INSERT INTO article(writer, title, contents) VALUES (?, ?, ?)";
    }

    static public String updateArticleQuery(){
        return "UPDATE article SET writer = ?, title = ?, contents = ? WHERE article.id = ?";
    }

    static public String findArticleByIdQuery(){
        return "SELECT * FROM article WHERE article.id = ?";
    }

    static public String findAllArticlesQuery(){
        return "SELECT * FROM article";
    }
}
