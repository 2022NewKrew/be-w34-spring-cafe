package com.kakao.cafe.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.article.ViewCount;
import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    User baseUser;

    @BeforeAll
    void setup() {
        baseUser = new User(UUID.randomUUID(), new UserName("MappingTest"), new Password("MappingTest"), new Name("MappingTest"), new Email("MappingTest@email.com"));
    }

    @Test
    void articleRegisterRequestDtoToArticle_Invoked_ReturnsCorrectArticle() {
        ArticleRegisterRequestDto base = new ArticleRegisterRequestDto("MappingTest", "MappingTest", "MappingTest");
        Article actual = articleMapper.articleRegisterRequestDtoToArticle(base, baseUser);

        assertThat(actual.getTitle().getValue()).isEqualTo(base.getTitle());
        assertThat(actual.getContent().getValue()).isEqualTo(base.getContent());
        assertThat(actual.getWriter()).isEqualTo(baseUser);
        assertThat(actual.getViewCount().getValue()).isEqualTo(0);
    }

    @Test
    void articleToArticleDetailResponseDto_Invoked_ReturnsCorrectDto() {
        Article base = new Article(UUID.randomUUID(), new Title("MappingTest"), new Content("MappingTest"), baseUser, LocalDateTime.of(2021,11,29,0,0), new ViewCount());
        ArticleDetailResponseDto actual = articleMapper.articleToArticleDetailResponseDto(base);

        assertThat(actual.getTitle()).isEqualTo(base.getTitle().getValue());
        assertThat(actual.getContent()).isEqualTo(base.getContent().getValue());
        assertThat(actual.getViewCount()).isEqualTo(base.getViewCount().getValue());
        assertThat(actual.getWriter()).isEqualTo(base.getWriter().getUserName().getValue());
        assertThat(actual.getCreatedAt()).isEqualTo(base.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    void articleToArticleDetailResponseDto_ContentContainsNewLine_ReturnsCorrectDtoWithBrTag() {
        Article base = new Article(UUID.randomUUID(), new Title("MappingTest"), new Content("MappingTest\nMappingTestNewLine"), baseUser, LocalDateTime.of(2021,11,29,0,0), new ViewCount());
        ArticleDetailResponseDto actual = articleMapper.articleToArticleDetailResponseDto(base);

        assertThat(actual.getTitle()).isEqualTo(base.getTitle().getValue());
        assertThat(actual.getContent()).isEqualTo(base.getContent().getValue().replace("\n", "<br>"));
        assertThat(actual.getViewCount()).isEqualTo(base.getViewCount().getValue());
        assertThat(actual.getWriter()).isEqualTo(base.getWriter().getUserName().getValue());
        assertThat(actual.getCreatedAt()).isEqualTo(base.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    void articleListToArticleListResponseDtoList_Invoked_ReturnsCorrectDtoList() {
        List<Article> base = List.of(
                new Article(UUID.randomUUID(), new Title("MappingTest1"), new Content("MappingTest1"), baseUser, LocalDateTime.of(2021,11,29,0,0), new ViewCount()),
                new Article(UUID.randomUUID(), new Title("MappingTest2"), new Content("MappingTest2"), baseUser, LocalDateTime.of(2021,11,29,0,0), new ViewCount(8))
        );
        List<ArticleListResponseDto> actual = articleMapper.articleListToArticleListResponseDtoList(base);

        assertThat(actual.get(0).getArticleId()).isEqualTo(base.get(0).getArticleId().toString());
        assertThat(actual.get(0).getTitle()).isEqualTo(base.get(0).getTitle().getValue());
        assertThat(actual.get(0).getCreatedAt()).isEqualTo(base.get(0).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertThat(actual.get(0).getWriter()).isEqualTo(base.get(0).getWriter().getUserName().getValue());
        assertThat(actual.get(0).getViewCount()).isEqualTo(base.get(0).getViewCount().getValue());
        assertThat(actual.get(1).getArticleId()).isEqualTo(base.get(1).getArticleId().toString());
        assertThat(actual.get(1).getTitle()).isEqualTo(base.get(1).getTitle().getValue());
        assertThat(actual.get(1).getCreatedAt()).isEqualTo(base.get(1).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertThat(actual.get(1).getWriter()).isEqualTo(base.get(1).getWriter().getUserName().getValue());
        assertThat(actual.get(1).getViewCount()).isEqualTo(base.get(1).getViewCount().getValue());
    }
}
