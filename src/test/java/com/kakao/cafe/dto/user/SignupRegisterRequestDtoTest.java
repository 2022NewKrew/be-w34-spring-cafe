package com.kakao.cafe.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@TestInstance(Lifecycle.PER_CLASS)
public class SignupRegisterRequestDtoTest {

    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_UserNameIsNullAndEmpty_Invalid(String username) {
        SignupRequestDto base = new SignupRequestDto(username, "password", "name", "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_PasswordIsNullAndEmpty_Invalid(String password) {
        SignupRequestDto base = new SignupRequestDto("username", password, "name", "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NameIsNullAndEmpty_Invalid(String name) {
        SignupRequestDto base = new SignupRequestDto("username", "password", name, "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_EmailIsNullAndEmpty_Invalid(String email) {
        SignupRequestDto base = new SignupRequestDto("username", "password", "name", email);
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @Test
    void constructor_EmailFormatNotMatched_Invalid() {
        SignupRequestDto base = new SignupRequestDto("username", "password", "name", "email");
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given.size()).isNotEqualTo(0);
    }

    @Test
    void constructor_AllParametersValid_Valid() {
        SignupRequestDto base = new SignupRequestDto("username", "password", "name", "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> given = validator.validate(base);
        assertThat(given).isEmpty();
    }
}
