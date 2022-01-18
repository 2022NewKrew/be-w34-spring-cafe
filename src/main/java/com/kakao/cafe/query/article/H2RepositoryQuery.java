package com.kakao.cafe.query.article;

public class H2RepositoryQuery {
    public static final String insertQuery = "insert into article (writer, title, contents) values(?, ?, ?)";
    public static final String selectAllQuery = "select * from article";
    public static final String selectByIdQuery = "select * from article where id = ?";
    public static final String deleteByIdQuery = "delete from article where id = ?";
    public static final String deleteByWriterQuery = "delete from article where writer = ?";
    public static final String updateQuery = "UPDATE ARTICLE SET contents = ? WHERE ID = ?";
}
