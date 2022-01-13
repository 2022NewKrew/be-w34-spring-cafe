package com.kakao.cafe.helper;

import com.kakao.cafe.model.Post;

public class PostHelper {
    private static final String regexOfTitle = "^.{1,30}$";
    private static final String regexOfContent = "^.{1,500}$";

    public static boolean checkRegexOfPost (Post post) {
        boolean checkResult = true;
        checkResult &= checkRegexOfTitle(post.getTitle());
        checkResult &= checkRegexOfContent(post.getContent());
        return checkResult;
    }

    private static boolean checkRegexOfTitle(String title) {
        return title != null && title.matches(regexOfTitle);
    }

    private static boolean checkRegexOfContent(String content) {
        return content != null && content.matches(regexOfContent);
    }
}
