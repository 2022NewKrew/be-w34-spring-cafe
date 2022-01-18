package com.kakao.cafe.query.user;

public class H2RepositoryQuery {
    public static final String insertQuery = "insert into user (userid, password, name, email) values (?,?,?,?)";
    public static final String selectAllQuery = "select * from user";
    public static final String selectByIdQuery = "select * from user where userid = ?";
    public static final String deleteByIdQuery = "delete from user where userid = ?";
}
