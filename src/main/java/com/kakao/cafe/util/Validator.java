package com.kakao.cafe.util;

import com.kakao.cafe.controller.dto.ArticleDto;
import com.kakao.cafe.controller.dto.UserFormDto;
import com.kakao.cafe.controller.dto.UserJoinDto;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public void newUserCheck(UserJoinDto user) {
        if(user.getUserId().length() > TableInfo.MAX_USER_ID_LEN)
            throw new IllegalArgumentException("id의 최대 길이는" + TableInfo.MAX_USER_ID_LEN + "입니다.");
        if(user.getEmail().length() > TableInfo.MAX_USER_EMAIL_LEN)
            throw new IllegalArgumentException("email의 최대 길이는 " + TableInfo.MAX_USER_EMAIL_LEN + "입니다.");
        if(user.getName().length() > TableInfo.MAX_USER_NAME_LEN)
            throw new IllegalArgumentException("유저 이름의 최대 길이는 " + TableInfo.MAX_USER_NAME_LEN + "입니다.");
        if(user.getPassword().length() > TableInfo.MAX_USER_PASSWORD_LEN)
            throw new IllegalArgumentException("비밀번호의 최대 길이는 " + TableInfo.MAX_USER_PASSWORD_LEN + "입니다.");
    }

    public void updateUserCheck(UserFormDto user) {
        if(user.getEmail().length() > TableInfo.MAX_USER_EMAIL_LEN)
            throw new IllegalArgumentException("email의 최대 길이는 " + TableInfo.MAX_USER_EMAIL_LEN + "입니다.");
        if(user.getName().length() > TableInfo.MAX_USER_NAME_LEN)
            throw new IllegalArgumentException("유저 이름의 최대 길이는 " + TableInfo.MAX_USER_NAME_LEN + "입니다.");
    }

    public void ArticleCheck(ArticleDto article) {
        if(article.getTitle().length() > TableInfo.MAX_ARTICLE_TITLE_LEN)
            throw new IllegalArgumentException("타이틀명의 최대 길이는 " + TableInfo.MAX_ARTICLE_TITLE_LEN + "입니다.");
        if(article.getContent().length() > TableInfo.MAX_ARTICLE_CONTENT_LEN)
            throw new IllegalArgumentException("글 내용 최대 길이는 " + TableInfo.MAX_ARTICLE_CONTENT_LEN + "입니다.");
    }
}
