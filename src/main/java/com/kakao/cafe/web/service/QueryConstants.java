package com.kakao.cafe.web.service;

public final class QueryConstants {
    public static final String USER_SELECT = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS";
    public static final String USER_INSERT = "INSERT INTO USERS (USERID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
    public static final String USER_SELECT_BY_ID = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID=?";
    public static final String USER_SELECT_BY_USERID = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID=?";
    public static final String USER_UPDATE = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE ID=?";

    public static final String ARTICLE_SELECT = "SELECT ID, AUTHOR_ID, CREATEDATE, TITLE, CONTENT FROM ARTICLES";
    public static final String ARTICLE_INSERT = "INSERT INTO ARTICLES (AUTHOR_ID, TITLE, CONTENT) VALUES (?, ?, ?)";
    public static final String ARTICLE_SELECT_BY_ID = "SELECT ID, AUTHOR_ID, TITLE, CONTENT, CREATEDATE FROM ARTICLES WHERE ID=?";
    public static final String ARTICLE_UPDATE = "UPDATE ARTICLES SET TITLE=?, CONTENT=? WHERE ID=?";
    public static final String ARTICLE_DELETE = "DELETE FROM ARTICLES WHERE ID=?";
}
