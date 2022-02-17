package com.kakao.cafe.advice;

import java.sql.*;

public class foo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://muscle-db.ay1.krane.9rum.cc:3306/test";
        String userName = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from USER");

        resultSet.next();
        String name = resultSet.getString("name");
        System.out.println(name);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
