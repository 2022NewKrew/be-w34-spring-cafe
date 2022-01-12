package com.kakao.cafe.helper;

import com.kakao.cafe.model.User;

public class UserHelper {

    private static final String regexOfEmail = "^[\\w&&[^_]]+@[a-z]+\\.[a-z]{2,6}$";

    public static boolean checkRegexOfUser (User user) {
        boolean checkResult = true;
        checkResult &= checkRegexOfId(user.getUserId());
        checkResult &= checkRegexOfPassword(user.getPassword());
        checkResult &= checkRegexOfEmail(user.getEmail());
        System.out.println(checkResult);
        return checkResult;
    }

    private static boolean checkRegexOfId (String userId) {
        return true;
    }
    private static boolean checkRegexOfPassword (String password) {
        return true;
    }
    private static boolean checkRegexOfEmail (String email) {
        return email != null && email.matches(regexOfEmail);
    }
}
