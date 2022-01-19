package com.kakao.cafe.article.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleDtoTest {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    @DisplayName("필수 값을 입력하지 않을시 에러 발생")
    void test1() {
        ArticleDto articleDto = ArticleDto.builder().id(1l).title(null).content("내용").createTime(LocalDateTime.now()).build();

        Set<ConstraintViolation<ArticleDto>> validations = validator.validate(articleDto);
        assertThat(validations.size()).isNotEqualTo(0);
        validations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("제목은 필수 입력값 입니다.");
        });
    }

}
