package com.kakao.cafe.article.repository;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.account.mapper.AccountMapper;
import com.kakao.cafe.account.repository.AccountRepository;
import com.kakao.cafe.account.repository.AccountRepositoryImpl;
import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.exception.custom.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJdbcTest
class ArticleRepositoryImplTest {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleRepositoryImplTest(JdbcTemplate jdbcTemplate) {
        this.articleRepository = new ArticleRepositoryImpl(jdbcTemplate);
    }

    private ArticleDto articleDto;

    private ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    @BeforeEach
    void createDto() {
        articleDto = ArticleDto.builder()
                .id(1l)
                .title("제목")
                .content("내용")
                .createTime(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("게시글 저장")
    void test1() {
        // given
        Article article = articleMapper.toEntity(articleDto);

        // when
        articleRepository.save(article);

        // then
        assertThat(articleRepository.findById(articleDto.getId()).getId())
                .isEqualTo(article.getId());
    }

    @Test
    @DisplayName("찾으려는 게시글이 없을시 에러")
    void test3() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> articleRepository.findById(2l));
    }
}
