package com.kakao.cafe.query.user;

public class H2RepositoryQuery {
    public static final String insertQuery = "insert into USER (userid, password, name, email) values (?,?,?,?)";
    public static final String selectAllQuery = "select * from USER";
    public static final String selectByIdQuery = "select * from USER where userid = ?";
    public static final String deleteByIdQuery = "delete from USER where userid = ?";
}
