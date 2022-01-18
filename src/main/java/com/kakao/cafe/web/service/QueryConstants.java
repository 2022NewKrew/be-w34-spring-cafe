package com.kakao.cafe.web.service;

public final class QueryConstants {
    public static final String userSelect = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS";
    public static final String userInsert = "INSERT INTO USERS (USERID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
    public static final String userSelectById = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID=?";
    public static final String userSelectByUserId = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID=?";
    public static final String userUpdate = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE ID=?";

    public static final String articleSelect = "SELECT ID, AUTHOR_ID, CREATEDATE, TITLE, CONTENT FROM ARTICLES";
    public static final String articleInsert = "INSERT INTO ARTICLES (AUTHOR_ID, TITLE, CONTENT) VALUES (?, ?, ?)";
    public static final String articleSelectById = "SELECT ID, AUTHOR_ID, TITLE, CONTENT, CREATEDATE FROM ARTICLES WHERE ID=?";
    public static final String articleUpdate = "UPDATE ARTICLES SET TITLE=?, CONTENT=? WHERE ID=?";
    public static final String articleDelete = "DELETE FROM ARTICLES WHERE ID=?";
}
