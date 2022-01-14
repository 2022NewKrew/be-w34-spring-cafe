package com.kakao.cafe.account.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AccountDtoTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @ParameterizedTest
    @MethodSource("getAccountInput")
    @DisplayName("필수 값을 입력하지 않을시 에러 발생")
    void test1(String userId, String name, String password, String email, String errorMsg) {
        validateValidations(new AccountDto(userId, name, password, email), errorMsg);
    }
    private static Stream<Arguments> getAccountInput() {
        return Stream.of(
                Arguments.arguments(null, "name", "password", "email@aaa.com", "아이디는 필수 입력값 입니다."),
                Arguments.arguments("", "name", "password", "email@aaa.com", "아이디는 필수 입력값 입니다."),
                Arguments.arguments(" ", "name", "password", "email@aaa.com", "아이디는 필수 입력값 입니다."),
                Arguments.arguments("userId", null, "password", "email@aaa.com", "이름은 필수 입력값 입니다."),
                Arguments.arguments("userId", "name", null, "email@aaa.com", "패스워드는 필수 입력값 입니다."),
                Arguments.arguments("userId", "name", "password", null, "이메일은 필수 입력값 입니다.")
        );
    }

    @Test
    @DisplayName("이메일 형식이 맞지 않으면 에러 발생")
    void test2() {
        validateValidations(new AccountDto("userId", "name", "password", "email"),
                "이메일 형식이 맞지 않습니다.");
    }

    @Test
    @DisplayName("비밀번호 형식이 맞지 않으면 에러 발생")
    void test3() {
        validateValidations(new AccountDto("userId", "name", "pass", "email@aaa.com")
                , "패스워드는 5글자 이상이어야 합니다.");
    }

    private void validateValidations(AccountDto accountDto, String errorMsg) {
        Set<ConstraintViolation<AccountDto>> validations = validator.validate(accountDto);
        assertThat(validations.size()).isNotEqualTo(0);
        validations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo(errorMsg);
        });
    }

}
