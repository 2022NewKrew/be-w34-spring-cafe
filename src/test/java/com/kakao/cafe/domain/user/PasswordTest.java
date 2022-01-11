package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PasswordTest {

    @Test
    void nullTest(){
        assertThatThrownBy(()->{
            new Password(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void lengthZeroTest(){
        assertThatThrownBy(() -> new Password("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void blankTest(){
        assertThatThrownBy(() -> new Password("  ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void passwordEqualTest(){
        Password password = new Password("1q2w3e4r");
        assertThat(password.is("1q2w3e4r")).isTrue();
    }
}
