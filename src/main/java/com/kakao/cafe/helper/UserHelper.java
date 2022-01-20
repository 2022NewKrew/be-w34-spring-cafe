package com.kakao.cafe.helper;

import com.kakao.cafe.model.User;

public class UserHelper {

    private static final String regexOfId = "^[\\w&&[^_]]{1,20}$";
    private static final String regexOfName = "^[\\w&&[^_]]{1,12}$";
    private static final String regexOfPassword = "^\\w{1,20}$";
    private static final String regexOfEmail = "^[\\w&&[^_]]+@[a-z]+\\.[a-z]{2,6}$";

    public static boolean checkRegexOfUser (User user) {
        return checkRegexOfId(user.getUserId()) && checkRegexOfPassword(user.getPassword()) && checkRegexOfEmail(user.getEmail()) && checkRegexOfName(user.getName());
    }

    public static boolean checkRegexOfUpdate (User user) {
        return checkRegexOfEmail(user.getEmail()) && checkRegexOfName(user.getName());
    }

    private static boolean checkRegexOfId (String userId) {
        return userId != null && userId.matches(regexOfId);
    }

    public static boolean checkRegexOfPassword (String password) {
        return password != null && password.matches(regexOfPassword);
    }

    private static boolean checkRegexOfEmail (String email) {
        return email != null && email.matches(regexOfEmail);
    }

    private static boolean checkRegexOfName (String name) {
        return name != null && name.matches(regexOfName);
    }
}
