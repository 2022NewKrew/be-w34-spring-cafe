package com.kakao.cafe.query.reply;

public class RepositoryQuery {
    public static final String selectByIdQuery = "select * from REPLY where FK_article_id = ?";
    public static final String insertQuery = "insert into REPLY (writer, contents, FK_article_id) values (?,?,?)";
    public static final String deleteByIdQuery = "delete from REPLY where id = ?";
}
