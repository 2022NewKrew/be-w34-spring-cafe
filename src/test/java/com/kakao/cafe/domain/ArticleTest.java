package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserFormDto;
import com.kakao.cafe.dto.UserMapper;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ArticleTest {

    @Test
    void createArticle() {
        User user = UserMapper.toUser(new UserFormDto("test123", "&test12345", "테스트", "test123@test.com"));
        new Article(0L, user, new Date(), new Title("123456"), new Contents("테스트용"));
    }
}
