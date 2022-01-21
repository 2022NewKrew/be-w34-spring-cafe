package com.kakao.cafe.dao.reply;

import com.kakao.cafe.model.reply.Reply;

public class ReplySql {

    public static String insert(Reply reply) {
        return String.format(
                "INSERT INTO REPLY(ARTICLE_ID, USER_ID, COMMENT) VALUES ('%s', '%s', '%s')",
                reply.getArticleId(),
                reply.getUserId().getValue(),
                reply.getComment().getValue());
    }

    public static String update(Reply reply) {
        return String.format(
                "UPDATE REPLY SET COMMENT = '%s' WHERE ID = '%s'",
                reply.getComment().getValue(),
                reply.getId());
    }

    public static String getAllReplyNotDeleted(int articleId) {
        return String.format(
                "SELECT ID, ARTICLE_ID, USER_ID, COMMENT, CREATE_DATE, DELETED FROM REPLY WHERE ARTICLE_ID = %s AND DELETED = FALSE ORDER BY CREATE_DATE ASC ",
                articleId);
    }

    public static String delete(int id) {
        return String.format(
                "UPDATE REPLY SET DELETED = TRUE WHERE ID = '%s'",
                id);
    }

    public static String findReplyById(int id) {
        return String.format(
                "SELECT ID, ARTICLE_ID, USER_ID, COMMENT, CREATE_DATE, DELETED FROM REPLY WHERE ID = %s AND DELETED = FALSE ORDER BY CREATE_DATE ASC ",
                id);
    }
}
