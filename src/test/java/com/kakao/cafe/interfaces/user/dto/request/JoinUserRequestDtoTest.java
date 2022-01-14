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

class JoinUserRequestDtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Stream<JoinUserRequestDto> createInvalidJoinUserRequestDtoList() {
        return Stream.of(
                        new JoinUserRequestDto("", "password", "name", "email@email.com"),
                        new JoinUserRequestDto("id", "", "name", "email@email.com"),
                        new JoinUserRequestDto("id", "password", "", "email@email.com"),
                        new JoinUserRequestDto("id", "password", "name", ""),
                        new JoinUserRequestDto("id", "password", "name", "email.com"),
                        new JoinUserRequestDto("id", "password", "name", "email@"),
                        new JoinUserRequestDto("id", "password", "name", "@email.com")
        );
    }

    @DisplayName("올바른 DTO가 들어오는지 확인한다")
    @Test
    void checkValidDto() {
        // given
        JoinUserRequestDto dto = new JoinUserRequestDto("id", "password", "name", "email@email.com");

        // when
        Set<ConstraintViolation<JoinUserRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations.isEmpty())
                .isTrue();
    }

    @DisplayName("유효하지 않은 DTO가 들어오는지 확인한다")
    @MethodSource("createInvalidJoinUserRequestDtoList")
    @ParameterizedTest
    void checkInvalidDto(JoinUserRequestDto joinUserRequestDto) {
        // when
        Set<ConstraintViolation<JoinUserRequestDto>> violations = validator.validate(joinUserRequestDto);

        // then
        assertThat(violations.isEmpty())
                .isFalse();
    }
}