package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.User;

public class UserSql {

    public static String getAllUser() {
        return "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA";
    }

    public static String insert(User user) {
        return String.format(
                "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES ('%s', '%s', '%s', '%s')",
                user.getUserId().getValue(),
                user.getPassword().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue());
    }

    public static String findUserById(String userId) {
        return String.format(
                "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA WHERE USER_ID = '%s'",
                userId
        );
    }

    public static String update(User user) {
        return String.format(
                "UPDATE USER_DATA SET NAME = '%s', EMAIL = '%s' WHERE USER_ID = '%s'",
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getUserId().getValue());
    }

    public static String count() {
        return "SELECT COUNT(USER_ID) FROM USER_DATA";
    }
}
