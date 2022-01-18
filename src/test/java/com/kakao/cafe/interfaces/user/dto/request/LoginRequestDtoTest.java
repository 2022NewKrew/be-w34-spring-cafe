package com.kakao.cafe.interfaces.user.dto.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LoginRequestDtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Stream<LoginRequestDto> createInvalidLoginRequestDtoList() {
        return Stream.of(
                new LoginRequestDto("", "password"),
                new LoginRequestDto("id", ""),
                new LoginRequestDto("", "")
        );
    }

    @DisplayName("올바른 DTO가 들어오는지 확인한다")
    @Test
    void checkValidDto() {
        // given
        LoginRequestDto dto = new LoginRequestDto("id", "password");

        // when
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations.isEmpty())
                .isTrue();
    }

    @DisplayName("올바르지 않은 로그인 DTO의 유효성을 검증한다")
    @MethodSource("createInvalidLoginRequestDtoList")
    @ParameterizedTest
    void checkInvalidDto(LoginRequestDto loginRequestDto) {
        // when
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

        //then
        assertThat(violations.isEmpty())
                .isFalse();
    }
}