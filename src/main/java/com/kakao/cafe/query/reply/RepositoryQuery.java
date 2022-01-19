package com.kakao.cafe.query.reply;

public class RepositoryQuery {
    public static final String selectByIdQuery = "select * from reply where FK_article_id = ?";
    public static final String insertQuery = "insert into reply (writer, contents, FK_article_id) values (?,?,?)";
    public static final String deleteByIdQuery = "delete from reply where id = ?";
}
