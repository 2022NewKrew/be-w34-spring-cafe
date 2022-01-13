package com.kakao.cafe.dto.article;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@TestInstance(Lifecycle.PER_CLASS)
public class ArticleRegisterRequestDtoTest {

    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_UserNameIsNullAndEmpty_Invalid(String username) {
        ArticleRegisterRequestDto base = new ArticleRegisterRequestDto(username, "title", "content");
        Set<ConstraintViolation<ArticleRegisterRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_TitleIsNullAndEmpty_Invalid(String title) {
        ArticleRegisterRequestDto base = new ArticleRegisterRequestDto("username", title, "content");
        Set<ConstraintViolation<ArticleRegisterRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_ContentIsNullAndEmpty_Invalid(String content) {
        ArticleRegisterRequestDto base = new ArticleRegisterRequestDto("username", "title", content);
        Set<ConstraintViolation<ArticleRegisterRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @Test
    void constructor_AllParametersValid_Valid() {
        ArticleRegisterRequestDto base = new ArticleRegisterRequestDto("username", "title", "content");
        Set<ConstraintViolation<ArticleRegisterRequestDto>> given = validator.validate(base);
        assertThat(given).isEmpty();
    }
}
