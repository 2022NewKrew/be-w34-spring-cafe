package com.kakao.cafe.domain;

import com.kakao.cafe.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private User user;

    private static final String DUMMY_ADDRESS = "/images/80-text.png";

    @BeforeEach
    void setup(){
        user = new User("a", "b", "c", "d@e");
    }

    @Test
    void testUserId() {
        assertThat(user.getUserId()).isEqualTo("a");
    }

    @Test
    void testName() {
        assertThat(user.getName()).isEqualTo("b");
        user.setName("anotherName");
        assertThat(user.getName()).isEqualTo("anotherName");
    }

    @Test
    void testEmail() {
        assertThat(user.getEmail()).isEqualTo("d@e");
        user.setEmail("foo@bar.com");
        assertThat(user.getEmail()).isEqualTo("foo@bar.com");
    }

    @Test
    void testPictureAddress() {
        assertThat(user.getPictureAddress()).isEqualTo(DUMMY_ADDRESS);
        user.setPictureAddress("/image/my_picture.jpeg");
        assertThat(user.getPictureAddress()).isEqualTo("/image/my_picture.jpeg");
    }

    @Test
    void setPassword() {
        //TODO password 관련한 로직 필요
        //예를들어 비밀번호가 있어야만 modify 가능한 user 객체를 넘겨준다던지
        //아니라면 read만 가능한 객체를 전달
    }
}