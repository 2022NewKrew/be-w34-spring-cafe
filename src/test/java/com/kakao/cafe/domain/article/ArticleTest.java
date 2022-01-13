package com.kakao.cafe.domain.article;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArticleTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 15, 2022})
    void increaseViewCount_Invoked_IncreaseViewCount(int viewCount) {
        User baseUser = new User(UUID.randomUUID(), new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        Article baseArticle = new Article(UUID.randomUUID(), new Title("testTitle"), new Content("testContent"), baseUser, LocalDateTime.now(), new ViewCount(viewCount));

        baseArticle.increaseViewCount();
        assertThat(baseArticle.getViewCount().getValue()).isEqualTo(viewCount + 1);
    }
}
