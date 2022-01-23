package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;

public class ArticleSql {

    public static String getAllArticleNotDeleted() {
        return "SELECT ID, TITLE, USER_ID, CONTENTS, CREATE_DATE, DELETED FROM ARTICLE WHERE DELETED = FALSE ORDER BY ID DESC";
    }

    public static String insert(Article article) {
        return String.format(
                "INSERT INTO ARTICLE(TITLE, USER_ID, CONTENTS) VALUES ('%s', '%s', '%s')",
                article.getTitle().getValue(),
                article.getUserId().getValue(),
                article.getContents().getValue());
    }

    public static String findArticleById(int id) {
        return String.format(
                "SELECT ID, TITLE, USER_ID, CONTENTS, CREATE_DATE, DELETED FROM ARTICLE WHERE ID = %s AND DELETED = FALSE",
                id);
    }

    public static String delete(int id) {
        return String.format("UPDATE ARTICLE SET DELETED = TRUE WHERE ID = %s AND DELETED = FALSE",
                id);
    }

    public static String update(Article article) {
        return String.format(
                "UPDATE ARTICLE SET TITLE = '%s', CONTENTS = '%s' WHERE ID = '%s' AND DELETED = FALSE",
                article.getTitle().getValue(),
                article.getContents().getValue(),
                article.getId());
    }

    public static String count() {
        return "SELECT COUNT(ID) FROM ARTICLE WHERE DELETED = FALSE";
    }
}
