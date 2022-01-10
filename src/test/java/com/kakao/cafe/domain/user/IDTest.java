package com.kakao.cafe.domain.user;


import com.kakao.cafe.domain.user.ID;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class IDTest {

    @Test
    void makeNullUser(){
        assertThatThrownBy(()->{
            new ID(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void lengthZeroUser(){
        assertThatThrownBy(()->{
            new ID("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void makeBlankUser(){
        assertThatThrownBy(()->{
            new ID("  ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
