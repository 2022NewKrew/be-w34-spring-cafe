package com.kakao.cafe.domain.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TitleTest {

    @ParameterizedTest
    @ValueSource(strings={"  ", ""})
    void initFailTest(String input){
        assertThatThrownBy(()->{
            new Title(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void nullFailTest(){
        assertThatThrownBy(()->{
            new Title(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
