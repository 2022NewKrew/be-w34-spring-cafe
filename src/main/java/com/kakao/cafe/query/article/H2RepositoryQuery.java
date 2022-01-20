package com.kakao.cafe.query.article;

public class H2RepositoryQuery {
    public static final String insertQuery = "insert into ARTICLE (writer, title, contents) values(?, ?, ?)";
    public static final String selectAllQuery = "select * from ARTICLE";
    public static final String selectByIdQuery = "select * from ARTICLE where id = ?";
    public static final String deleteByIdQuery = "delete from ARTICLE where id = ?";
    public static final String deleteByWriterQuery = "delete from ARTICLE where writer = ?";
    public static final String updateQuery = "UPDATE ARTICLE SET title = ?, contents = ? WHERE ID = ?";
}
